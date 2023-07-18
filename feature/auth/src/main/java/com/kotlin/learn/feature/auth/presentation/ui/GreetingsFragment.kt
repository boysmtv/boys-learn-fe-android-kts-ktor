package com.kotlin.learn.feature.auth.presentation.ui

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.security.SecurePrefManager
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.feature.auth.databinding.FragmentGreetingsBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.GreetingsViewModel
import com.kotlin.learn.feature.auth.util.common.GoogleSignInExt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingsFragment : BaseFragment<FragmentGreetingsBinding>(FragmentGreetingsBinding::inflate) {

    @Inject
    lateinit var securePrefManager: SecurePrefManager

    private val viewModel: GreetingsViewModel by viewModels()

    private lateinit var googleSignInExt: GoogleSignInExt

    @Inject
    lateinit var authNavigator: AuthNavigator

    override fun setupView() {
        setupInit()
        setupListener()
    }

    private fun setupInit() {
        googleSignInExt = GoogleSignInExt(requireContext())
    }

    private fun setupListener() = with(binding) {
        btnEmail.setOnClickListener {
            authNavigator.fromGreetingsToAuth(this@GreetingsFragment)
        }
        btnGoogle.setOnClickListener {
            signInGoogle()
        }
    }

    private fun signInGoogle() = with(googleSignInExt) {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, signInReqCore)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = with(googleSignInExt) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == signInReqCore) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(context) != null) {
//            Log.e("TAG", "BOYS-Done : " +
//                    securePrefManager.getString(PreferenceConstants.Auth.PREF_GOOGLE_AUTH)?.let {
//                        jsonUtil.fromJson<AuthGoogleSignInModel>(it)
//                    })
        }
    }

}