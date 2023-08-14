package com.kotlin.learn.feature.auth.presentation.ui

import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.SpringParser
import com.kotlin.learn.core.common.util.network.invokeSpringParser
import com.kotlin.learn.core.common.util.network.parseResultError
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.auth.databinding.FragmentAuthBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val userViewModel: UserViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    private var userModel: UserModel = UserModel()

    override fun setupView() {
        subscribeLogin()
        setupListener()
    }

    private fun subscribeLogin() = with(userViewModel) {
        postAuth.launch(this@AuthFragment) { result ->
            when (result) {
                Result.Waiting -> {}

                is Result.Loading -> showHideProgress(isLoading = true)

                is Result.Success -> postAuthSuccess(result)

                is Result.Error -> postAuthError(result.throwable)
            }
        }

        getUser.launch(this@AuthFragment) { result ->
            when (result) {
                Result.Waiting -> {}

                is Result.Loading -> showHideProgress(isLoading = true)

                is Result.Success -> getUserSuccess(result)

                is Result.Error -> getUserError(result.throwable)
            }
        }
    }

    private fun setupListener() = with(binding) {
        btnLogin.setOnClickListener {
            userModel.apply {
                email = etEmail.text.toString()
                password = etPassword.text.toString()
            }
            userViewModel.postAuth(
                UserModel().apply {
                    email = userModel.email
                    password = userModel.password
                }
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

    private fun postAuthSuccess(result: Result.Success<BaseResponse<LoginRespModel>>) {
        showHideProgress(isLoading = false)
        invokeSpringParser(result.data).launch(lifecycleOwner = this@AuthFragment) {
            when (it) {
                is SpringParser.Success -> {
                    if (it.data != null) {
                        jsonUtil.toJson(
                            userModel.apply {
                                id = it.data!!.id ?: Constant.EMPTY_STRING
                                displayName = it.data!!.fullName ?: Constant.EMPTY_STRING
                            }
                        )
                        getUserFromServer()
                    } else showDialogGeneralError("Register Error", "Error store data google register")
                }

                is SpringParser.Error -> {
                    showDialogGeneralError(
                        title = "Login Error",
                        message = "Check your connection"
                    )
                }
            }
        }
    }

    private fun postAuthError(throwable: Throwable) {
        parseResultError(throwable).launch(this@AuthFragment) { parser ->
            when (parser) {
                is SpringParser.Success -> {
                    showDialogGeneralError("Login failed", parser.data.toString())
                }

                is SpringParser.Error -> {
                    showDialogGeneralError("Login error", throwable.message.toString())
                }
            }
        }
    }

    private fun getUserFromServer() {
        userViewModel.getUser(
            UserModel().apply {
                id = userModel.id
                email = userModel.email
            }
        )
    }

    private fun getUserSuccess(result: Result.Success<BaseResponse<UserModel>>) {
        showHideProgress(isLoading = false)
        invokeSpringParser(result.data).launch(lifecycleOwner = this@AuthFragment) {
            when (it) {
                is SpringParser.Success -> {
                    if (it.data != null) {
                        userModel = it.data!!
                        userViewModel.storeUserToDatastore(
                            jsonUtil.toJson(userModel)
                        ).launch(this@AuthFragment) { event ->
                            invokeDataStoreEvent(event,
                                isFetched = {},
                                isStored = {
                                    authNavigator.fromAuthToHome(fragment = this@AuthFragment)
                                }
                            )
                        }
                    } else showDialogGeneralError("Get Profile Error", "Error store data profile")
                }

                is SpringParser.Error -> {
                    showDialogGeneralError(
                        title = "Login Error",
                        message = "Check your connection"
                    )
                }
            }
        }

    }

    private fun getUserError(throwable: Throwable) {
        parseResultError(throwable).launch(this@AuthFragment) { parser ->
            when (parser) {
                is SpringParser.Success -> {
                    showDialogGeneralError("Get Profile failed", parser.data.toString())
                }

                is SpringParser.Error -> {
                    showDialogGeneralError("Get Profile error", throwable.message.toString())
                }
            }
        }
    }

}