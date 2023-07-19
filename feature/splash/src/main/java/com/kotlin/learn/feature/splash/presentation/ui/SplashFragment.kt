package com.kotlin.learn.feature.splash.presentation.ui

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.splash.databinding.FragmentSplashBinding
import com.kotlin.learn.feature.splash.presentation.viewmodel.SplashViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    @Inject
    lateinit var authNavigator: AuthNavigator

    private val viewModel: SplashViewModel by viewModels()

    private var moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private var jsonAdapter: JsonAdapter<AuthGoogleSignInModel> = moshi.adapter(AuthGoogleSignInModel::class.java)

    override fun setupView() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.fetchDataAuth().launch(this) {
                invokeDataStoreEvent(it,
                    isSuccessFetch = { data ->
                        if (data.isNotEmpty()) {
                            val model = jsonAdapter.fromJson(data)
                            if (model!!.displayName != Constant.EMPTY_STRING)
                                lunchToHome()
                            else lunchToGreetings()
                        } else lunchToGreetings()
                    }, isSuccessStore = {})
            }
        }, 1500)
    }

    private fun lunchToHome() {
        authNavigator.fromSplashToHome(this@SplashFragment)
    }

    private fun lunchToGreetings() {
        authNavigator.fromSplashToGreetings(this@SplashFragment)
    }

}