package com.kotlin.learn.feature.auth.presentation.ui

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.feature.auth.databinding.FragmentGreetingsBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.GreetingsViewModel
import com.kotlin.learn.feature.auth.util.common.GoogleSignInExt
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
        subscribeDataAuth()
    }

    private fun setupInit() {
        googleSignInExt.initGoogle(requireContext())
    }

    private fun setupListener() = with(binding) {
        btnFacebook.setOnClickListener {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.fetchDataAuth().collect {
                        updateDataStoreEvent(it)
                    }
                }
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

    private fun subscribeDataAuth() = with(viewModel) {
        GoogleSignIn.getLastSignedInAccount(context)?.let {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    fetchDataAuth().collect {
                        updateDataStoreEvent(it)
                    }
                }
            }
        }
    }

    private fun invokeResultDataAuth(authGoogleSignInModel: AuthGoogleSignInModel) = with(viewModel) {
        val authJson = jsonAdapter.toJson(authGoogleSignInModel)
        Log.e("TAG", "BOYS-authJson : $authJson")

        runBlocking {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    storeDataAuth(authJson).collect {
                        updateDataStoreEvent(it)
                    }
                }
            }
        }
    }

    private fun updateDataStoreEvent(
        event: DataStoreCacheEvent
    ) {
        when (event) {
            is DataStoreCacheEvent.StoreSuccess -> {
                Toast.makeText(requireContext(), "Cache is success", Toast.LENGTH_SHORT).show()
            }

            is DataStoreCacheEvent.FetchSuccess -> {
                if (event.auth.isNotEmpty()) {
                    val authJson = jsonAdapter.fromJson(event.auth)
                    Log.e("TAG", "BOYS-FetchSuccess : $authJson")
                    Toast.makeText(
                        requireContext(),
                        "This is Cache Name : ${authJson?.displayName}",
                        Toast.LENGTH_SHORT
                    ).show()

                    if (authJson?.account != Constant.EMPTY_STRING)
                        authNavigator.fromGreetingsToHome(this@GreetingsFragment)
                } else Toast.makeText(
                    requireContext(),
                    "This is Cache Name : Empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}