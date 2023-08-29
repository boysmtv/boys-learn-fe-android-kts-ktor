package com.kotlin.learn.feature.auth.presentation.ui

import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.SpringParser
import com.kotlin.learn.core.common.util.network.invokeSpringParser
import com.kotlin.learn.core.common.util.network.parseResultError
import com.kotlin.learn.core.model.AuthMethod
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

    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    private var userModel: UserModel = UserModel()

    override fun setupView() {
        subscribeLogin()
        setupListener()
    }

    private fun subscribeLogin() = with(viewModel) {
        postAuth.launch(this@AuthFragment) { result ->
            when (result) {
                is Result.Waiting -> {}

                is Result.Loading -> showHideProgress(isLoading = true)

                is Result.Success -> postAuthSuccess(result)

                is Result.Error -> postAuthError(result.throwable)
            }
        }

        getUser.launch(this@AuthFragment) { result ->
            when (result) {
                is Result.Waiting -> {}

                is Result.Loading -> { /* TODO: skip loading after success post auth */
                }

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

            /* TODO : Disable for move to firestore
            userViewModel.postAuth(
                UserModel().apply {
                    email = userModel.email
                    password = userModel.password
                }
            )*/

            // TODO : Refactor fo firestore
            getUserFromFirestore()
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
                    showHideProgress(isLoading = false)
                    showDialogGeneralError(
                        title = "Login Error",
                        message = "Check your connection"
                    )
                }
            }
        }
    }

    private fun postAuthError(throwable: Throwable) {
        showHideProgress(isLoading = false)
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
        viewModel.getUser(
            UserModel().apply {
                id = userModel.id
                email = userModel.email
                method = AuthMethod.EMAIL.name
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
                        saveUserToDataStore()
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
        showHideProgress(isLoading = false)
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

    private fun getUserFromFirestore(){
        viewModel.fetchUserFromFirestore(
            filter = hashMapOf(
                "email" to userModel.email!!,
            ),
            onLoad = {
                showHideProgress(isLoading = true)
            },
            onSuccess = {
                showHideProgress(isLoading = false)
                if (it.password != null) {
                    if (it.password.equals(userModel.password)) {
                        userModel = it
                        saveUserToDataStore()
                    } else
                        showDialogGeneralError("Login failed", "Invalid username or password")
                } else
                    showDialogGeneralError("Login failed", "Error get profile")
            },
            onError = {
                showHideProgress(isLoading = false)
                showDialogGeneralError("Login failed", it)
            }
        )
    }

    private fun saveUserToDataStore(){
        viewModel.storeUserToDatastore(
            jsonUtil.toJson(userModel)
        ).launch(this@AuthFragment) { event ->
            invokeDataStoreEvent(event,
                isFetched = {},
                isStored = {
                    authNavigator.fromAuthToHome(fragment = this@AuthFragment)
                }
            )
        }
    }
}