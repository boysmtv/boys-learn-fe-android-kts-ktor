package com.kotlin.learn.feature.auth.presentation.ui

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.auth.databinding.FragmentGreetingsBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.GreetingsViewModel
import com.kotlin.learn.feature.auth.util.common.GoogleSignInExt
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingsFragment : BaseFragment<FragmentGreetingsBinding>(FragmentGreetingsBinding::inflate) {

    private val viewModel: GreetingsViewModel by viewModels()

    private var googleSignInExt: GoogleSignInExt = GoogleSignInExt(
        resultDataAuth = this::invokeResultDataAuth
    )

    @Inject
    lateinit var authNavigator: AuthNavigator

    private var moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private var jsonAdapter: JsonAdapter<AuthGoogleSignInModel> = moshi.adapter(AuthGoogleSignInModel::class.java)

    override fun setupView() {
        setupInit()
        setupListener()
    }

    private fun setupInit() {
        googleSignInExt.initGoogle(requireContext())
    }

    private fun setupListener() = with(binding) {
        btnFacebook.setOnClickListener {
            viewModel.fetchDataAuth().launch(this@GreetingsFragment) {
                updateDataStoreEvent(it)
            }
        }
        btnGoogle.setOnClickListener {
            signInGoogle()
        }
        btnEmail.setOnClickListener {
            authNavigator.fromGreetingsToAuth(this@GreetingsFragment)
        }
    }

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

    private fun invokeResultDataAuth(authGoogleSignInModel: AuthGoogleSignInModel) = with(viewModel) {
        storeDataAuth(jsonAdapter.toJson(authGoogleSignInModel)).launch(this@GreetingsFragment) {
            updateDataStoreEvent(it)
        }
    }

    private fun updateDataStoreEvent(event: DataStoreCacheEvent) {
        invokeDataStoreEvent(event,
            isSuccessStore = {
                authNavigator.fromGreetingsToHome(this@GreetingsFragment)
            }, {}
        )
    }

}