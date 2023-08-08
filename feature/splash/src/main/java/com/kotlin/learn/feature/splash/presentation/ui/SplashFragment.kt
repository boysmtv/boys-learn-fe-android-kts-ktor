package com.kotlin.learn.feature.splash.presentation.ui

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.google.GoogleSignInExt
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.splash.databinding.FragmentSplashBinding
import com.kotlin.learn.feature.splash.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    @Inject
    lateinit var jsonUtil: JsonUtil

    private var googleSignInExt: GoogleSignInExt = GoogleSignInExt({}, {})

    override fun setupView() {
        init()
        setupListener()
    }

    private fun init() {
        googleSignInExt.initGoogle(requireContext())
    }

    private fun setupListener() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.fetchDataAuth().launch(this) { event ->
                invokeDataStoreEvent(event,
                    isFetched = { data ->
                        if (data.isNotEmpty())
                            jsonUtil.fromJson<AuthGoogleSignInModel>(data)?.let {
                                if (it.displayName != Constant.EMPTY_STRING) launchToHome()
                                else navigateToGreetings()
                            }
                        else navigateToGreetings()
                    }, {}
                )
            }
        }, 100)
    }

    private fun launchToHome() {
        authNavigator.fromSplashToHome(this@SplashFragment)
    }

    private fun launchToGreetings() {
        authNavigator.fromSplashToGreetings(this@SplashFragment)
    }

    private fun signOutGoogle() = googleSignInExt.signOut({}, {})

    private fun navigateToGreetings() {
        viewModel.clearPreferences()
        signOutGoogle()
        launchToGreetings()
    }

}