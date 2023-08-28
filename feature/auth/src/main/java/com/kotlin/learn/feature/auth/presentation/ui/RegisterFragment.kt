package com.kotlin.learn.feature.auth.presentation.ui

import android.util.Log
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.SpringParser
import com.kotlin.learn.core.common.util.network.invokeSpringParser
import com.kotlin.learn.core.common.util.network.parseResultError
import com.kotlin.learn.core.model.AuthMethod
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialog
import com.kotlin.learn.core.utilities.Constant.EMPTY_STRING
import com.kotlin.learn.core.utilities.TransactionUtil
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.auth.R
import com.kotlin.learn.feature.auth.databinding.FragmentRegisterBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    private var userModel: UserModel = UserModel()

    private var token: String = EMPTY_STRING

    private val transactionId = TransactionUtil.generateTransactionID()

    override fun setupView() {
        init()
        fetchTokenFromDatastore()
        subscribeRegister()
        setupListener()
    }

    private fun init() = with(binding) {
        cvRegister.setBackgroundResource(R.drawable.card_rounded_top)
    }

    private fun fetchTokenFromDatastore() = with(viewModel) {
        fetchTokenFromDatastore().launch(this@RegisterFragment) {
            invokeDataStoreEvent(it,
                isFetched = { message ->
                    token = message
                }, {}
            )
        }
    }

    private fun subscribeRegister() = with(viewModel) {
        postUser.launch(this@RegisterFragment) {
            when (it) {
                is Result.Waiting -> {}

                is Result.Loading -> showHideProgress(isLoading = true)

                is Result.Success -> parseRegisterSuccess(it.data)

                is Result.Error -> parseRegisterError(it.throwable)
            }
        }
    }

    private fun parseRegisterSuccess(response: BaseResponse<RegisterRespModel>) {
        showHideProgress(isLoading = false)

        invokeSpringParser(response).launch(this@RegisterFragment) {
            when (it) {
                is SpringParser.Success -> {
                    viewModel.storeUserToDatastore(
                        jsonUtil.toJson(
                            userModel.apply {
                                id = it.data?.id ?: EMPTY_STRING
                            }
                        )
                    ).launch(this@RegisterFragment) { event ->
                        invokeDataStoreEvent(event,
                            isFetched = {},
                            isStored = {
                                showDialogSuccessRegister(it.data?.fullName ?: EMPTY_STRING)
                            }
                        )
                    }

                }

                is SpringParser.Error -> {
                    Log.e("Tag", "Register-ResultResponse.Error: $it")
                    showDialogGeneralError("Register Error", "Check your connection")
                }
            }
        }
    }

    private fun parseRegisterError(throwable: Throwable) {
        showHideProgress(isLoading = false)

        parseResultError(throwable).launch(this@RegisterFragment) { parser ->
            when (parser) {
                is SpringParser.Success -> {
                    showDialogGeneralError("Register failed", parser.data.toString())
                }

                is SpringParser.Error -> {
                    showDialogGeneralError("Register error", throwable.message.toString())
                }
            }
        }
    }

    private fun setupListener() = with(binding) {
        tvLogin.setOnClickListener {
            authNavigator.fromRegisterToAuth(this@RegisterFragment)
        }

        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnRegister.setOnClickListener {

            /*TODO : Disable for move to firestore
            userViewModel.postUser(
                userModel.apply {
                    idFireStore = EMPTY_STRING
                    idGoogle = EMPTY_STRING
                    idToken = EMPTY_STRING
                    firstName = etFirstName.text.toString()
                    lastName = etLastName.text.toString()
                    displayName = etFirstName.text.toString() + " " + etLastName.text.toString()
                    email = etEmail.text.toString()
                    phone = etPhone.text.toString()
                    photoUrl = EMPTY_STRING
                    password = etPassword.text.toString()
                    method = AuthMethod.EMAIL.name
                }
            )*/

            // TODO : Refactor fo firestore
            userModel.apply {
                id = transactionId
                idFireStore = EMPTY_STRING
                idGoogle = EMPTY_STRING
                idToken = token
                firstName = etFirstName.text.toString()
                lastName = etLastName.text.toString()
                displayName = etFirstName.text.toString() + " " + etLastName.text.toString()
                email = etEmail.text.toString()
                phone = etPhone.text.toString()
                photoUrl = EMPTY_STRING
                password = etPassword.text.toString()
                method = AuthMethod.EMAIL.name
            }
            getUserFromFirestore()
        }

//        etFirstName.setText("Dedy")
//        etLastName.setText("Wijaya")
//        etPhone.setText("08989996305")
//        etEmail.setText("Boys.mtv@gmail.com")
//        etPassword.setText("123456789")
    }

    private fun getUserFromFirestore() {
        viewModel.fetchUserFromFirestore(
            filter = hashMapOf(
                "email" to userModel.email!!,
            ),
            onLoad = {
                showHideProgress(isLoading = true)
            },
            onSuccess = {
                showHideProgress(isLoading = false)
                userModel = it
                showDialogGeneralError("Register failed", "Data already created.")
            },
            onError = {
                showHideProgress(isLoading = false)
                storeUserToFirestore()
            }
        )
    }

    private fun storeUserToFirestore() = with(binding) {
        viewModel.storeUserToFirestore(
            id = transactionId,
            model = userModel,
            onLoad = {
                showHideProgress(isLoading = true)
            },
            onSuccess = {
                showHideProgress(isLoading = false)
                showDialogSuccessRegister(userModel.displayName ?: EMPTY_STRING)
            },
            onError = {
                showHideProgress(isLoading = false)
                showDialogGeneralError("Register failed", it)
            }
        )
    }

    private fun showDialogSuccessRegister(name: String) {
        val content = BaseDataDialog(
            title = "Welcome, $name",
            content = "Your account already success created",
            primaryButtonShow = true,
            secondaryButtonText = EMPTY_STRING,
            secondaryButtonShow = false,
            icon = R.drawable.ic_warning_rounded,
            primaryButtonText = "Login"
        )
        showDialogWithActionButton(
            dataToDialog = content,
            actionClickPrimary = {
                authNavigator.fromRegisterToAuth(this@RegisterFragment)
            },
            tag = RegisterFragment::class.simpleName.toString()
        )
    }

}