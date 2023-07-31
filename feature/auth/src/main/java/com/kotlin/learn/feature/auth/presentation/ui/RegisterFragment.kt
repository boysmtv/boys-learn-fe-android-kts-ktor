package com.kotlin.learn.feature.auth.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
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
                Result.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }

                is Result.Success -> {
                    Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_LONG).show()
                    Log.e("Tag", "Register-Result.Success: ${it.data}")
                }

                is Result.Error -> {
                    Toast.makeText(requireContext(), "Register Error ${it.throwable.message}", Toast.LENGTH_LONG).show()
                    Log.e("Tag", "Register-Result.Error: ${it.throwable}")
                    Log.e("Tag", "Register-Result.Error: ${it.throwable.message}")
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