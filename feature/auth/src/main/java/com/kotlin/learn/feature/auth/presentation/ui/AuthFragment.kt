package com.kotlin.learn.feature.auth.presentation.ui

import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.SpringResult
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.invokeSpringResult
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
                is Result.Loading -> {}

                is Result.Success -> {
                    invokeSpringResult(result.data).launch(lifecycleOwner = this@AuthFragment) {
                        when (it) {
                            is SpringResult.Success -> authNavigator.fromAuthToHome(fragment = this@AuthFragment)

                            is SpringResult.Error -> {
                                showDialogGeneralError(
                                    title = "Login Error",
                                    message = "Check your connection"
                                )
                            }
                        }
                    }
                }

                is Result.Error -> {
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