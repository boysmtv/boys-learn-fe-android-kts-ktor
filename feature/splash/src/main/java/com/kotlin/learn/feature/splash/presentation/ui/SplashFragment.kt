package com.kotlin.learn.feature.splash.presentation.ui

import android.os.Handler
import android.os.Looper
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.feature.splash.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    @Inject
    lateinit var authNavigator: AuthNavigator

    override fun setupView() {
        Handler(Looper.getMainLooper()).postDelayed({
            authNavigator.fromSplashToGreetings(this@SplashFragment)
        }, 3000)
    }

    override fun setupListener() {

    }

}