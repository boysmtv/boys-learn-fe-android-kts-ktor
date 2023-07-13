package com.kotlin.learn.catalog.auth.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kotlin.learn.catalog.auth.presentation.viewmodel.AuthViewModel
import com.kotlin.learn.catalog.core.nav.navigator.AuthNavigator
import com.kotlin.learn.catalog.feature.auth.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
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