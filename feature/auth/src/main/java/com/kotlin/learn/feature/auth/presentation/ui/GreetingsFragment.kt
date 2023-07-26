package com.kotlin.learn.feature.auth.presentation.ui

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.auth.databinding.FragmentGreetingsBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.GreetingsViewModel
import com.kotlin.learn.feature.auth.util.common.GoogleSignInExt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingsFragment : BaseFragment<FragmentGreetingsBinding>(FragmentGreetingsBinding::inflate) {

    private val viewModel: GreetingsViewModel by viewModels()

    private var googleSignInExt: GoogleSignInExt = GoogleSignInExt(
        resultDataAuthSuccess = this::invokeResultDataAuthSuccess,
        resultDataAuthError = this::invokeResultDataAuthError
    )

    @Inject
    lateinit var authNavigator: AuthNavigator

    @Inject
    lateinit var jsonUtil: JsonUtil

    override fun setupView() {
        subscribeStoreAuth()
        setupInit()
        setupListener()
    }

    private fun subscribeStoreAuth() = with(viewModel) {
        storeFirebase.launch(this@GreetingsFragment) {

        }
    }

    private fun setupInit() {
        googleSignInExt.initGoogle(requireContext())
    }

    private fun setupListener() = with(binding) {
        btnFacebook.setOnClickListener {

        }
        btnGoogle.setOnClickListener {
            signInGoogle()
        }
        btnEmail.setOnClickListener {
            authNavigator.fromGreetingsToAuth(this@GreetingsFragment)
        }
    }

    // start region google login
    private fun signInGoogle() = with(googleSignInExt) {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityIntent.launch(signInIntent)
    }

    private var startActivityIntent: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        it?.let {
            with(googleSignInExt) {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                handleResult(task)
            }
        }
    }

    private fun invokeResultDataAuthSuccess(model: AuthGoogleSignInModel) = with(viewModel) {
        postDataAuthFirebase(model,
            onSuccess = { key ->
                storeDataAuth(jsonUtil.toJson(
                    model.apply {
                        firebaseId = key
                    }
                )).launch(this@GreetingsFragment) { event ->
                    invokeDataStoreEvent(event,
                        isFetched = {},
                        isStored = {
                            authNavigator.fromGreetingsToHome(this@GreetingsFragment)
                        }
                    )
                }
            },
            onError = {
                Toast.makeText(context, "Error Store Data Firebase", Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun invokeResultDataAuthError(message: String) {
        Log.e("Greetings", "Error Invoke Data Auth - Msg : $message")
        Toast.makeText(context, "Error Invoke Data Auth - Msg : $message", Toast.LENGTH_LONG).show()
    }
    // end region google login

}