package com.kotlin.learn.feature.auth.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.feature.auth.databinding.FragmentAuthBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    override fun setupView() {

    }

    private fun setupListener() = with(binding) {
        btnLogin.setOnClickListener {
            authNavigator.fromAuthToHome(this@AuthFragment)
        }

        tvRegister.setOnClickListener {
            authNavigator.fromAuthToRegister(this@AuthFragment)
        }
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

}