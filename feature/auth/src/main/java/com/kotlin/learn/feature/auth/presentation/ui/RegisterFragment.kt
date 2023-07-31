package com.kotlin.learn.feature.auth.presentation.ui

import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
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
        setupListener()
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
    }

}