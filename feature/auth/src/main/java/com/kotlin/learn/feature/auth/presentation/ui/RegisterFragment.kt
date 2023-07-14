package com.kotlin.learn.feature.auth.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kotlin.learn.feature.auth.presentation.viewmodel.RegisterViewModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.feature.auth.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        setupListener()
    }

    private fun setupView() {

    }

    private fun setupListener() {

    }
}