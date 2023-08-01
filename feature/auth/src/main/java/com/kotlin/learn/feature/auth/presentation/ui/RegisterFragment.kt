package com.kotlin.learn.feature.auth.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.SpringResultResponse
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.invokeResultResponse
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialog
import com.kotlin.learn.core.utilities.Constant.EMPTY_STRING
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.auth.R
import com.kotlin.learn.feature.auth.databinding.FragmentRegisterBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    override fun setupView() = with(binding) {
        cvRegister.setBackgroundResource(R.drawable.card_rounded_top)
        subscribeRegister()
        setupListener()
    }

    private fun subscribeRegister() = with(viewModel) {
        register.launch(this@RegisterFragment) {
            when (it) {
                Result.Loading -> { }

                is Result.Success -> {
                    parseRegisterSuccess(it.data)
                }

                is Result.Error -> {
                    showDialogGeneralError("Register Error", it.throwable.message.toString())
                }
            }
        }
    }

    private fun parseRegisterSuccess(response: BaseResponse<RegisterRespModel>) {
        invokeResultResponse(response).launch(this@RegisterFragment) {
            when (it) {
                is SpringResultResponse.Success -> {
                    Log.e("Tag", "Register-ResultResponse.Success: ${it.data}")
                    val content = BaseDataDialog(
                        title = "Welcome",
                        content = "Your account already success created",
                        primaryButtonShow = true,
                        secondaryButtonText = EMPTY_STRING,
                        secondaryButtonShow = false,
                        icon = R.drawable.ic_warning_rounded,
                        primaryButtonText = "Login"
                    )
                    showDialogWithActionButton(
                        dataToDialog = content,
                        actionClickPrimary = {},
                        tag = RegisterFragment::class.simpleName.toString()
                    )
                }

                is SpringResultResponse.Error -> {
                    Log.e("Tag", "Register-ResultResponse.Error: $it")
                    showDialogGeneralError("Register Error", "Check your connection")
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
            viewModel.postRegister(
                RegisterReqModel(
                    firstName = etFirstName.text.toString(),
                    lastName = etLastName.text.toString(),
                    phoneNumber = etPhone.text.toString(),
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            )
        }

        etFirstName.setText("Dedy")
        etLastName.setText("Wijaya")
        etPhone.setText("08989996305")
        etEmail.setText("Boys.mtv@gmail.com")
        etPassword.setText("123456789")
    }

}