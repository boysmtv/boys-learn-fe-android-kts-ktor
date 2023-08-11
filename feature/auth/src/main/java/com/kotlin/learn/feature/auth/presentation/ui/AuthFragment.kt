package com.kotlin.learn.feature.auth.presentation.ui

import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.SpringParser
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.network.invokeSpringParser
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.auth.databinding.FragmentAuthBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    override fun setupView() {
        subscribeLogin()
        setupListener()
    }

    private fun subscribeLogin() = with(viewModel) {
        login.launch(this@AuthFragment) { result ->
            when (result) {
                Result.Waiting -> {}

                is Result.Loading -> showHideProgress(isLoading = true)

                is Result.Success -> {
                    showHideProgress(isLoading = false)
                    invokeSpringParser(result.data).launch(lifecycleOwner = this@AuthFragment) {
                        when (it) {
                            is SpringParser.Success -> authNavigator.fromAuthToHome(fragment = this@AuthFragment)

                            is SpringParser.Error -> {
                                showDialogGeneralError(
                                    title = "Login Error",
                                    message = "Check your connection"
                                )
                            }
                        }
                    }
                }

                is Result.Error -> {
                    showHideProgress(isLoading = false)
                    showDialogGeneralError(
                        title = "Login Error",
                        message = result.throwable.message.toString()
                    )
                }
            }
        }
    }

    private fun setupListener() = with(binding) {
        btnLogin.setOnClickListener {
            viewModel.postLogin(
                LoginReqModel(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            )
        }

        tvRegister.setOnClickListener {
            authNavigator.fromAuthToRegister(this@AuthFragment)
        }

        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        etEmail.setText("Boys.mtv@gmail.com")
        etPassword.setText("123456789")
    }
}