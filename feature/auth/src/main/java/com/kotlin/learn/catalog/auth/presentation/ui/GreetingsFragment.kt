package com.kotlin.learn.catalog.auth.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kotlin.learn.catalog.auth.presentation.viewmodel.GreetingsViewModel
import com.kotlin.learn.catalog.feature.auth.databinding.FragmentGreetingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GreetingsFragment : Fragment() {

    private lateinit var binding: FragmentGreetingsBinding

    private val viewModel: GreetingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGreetingsBinding.inflate(inflater, container, false)
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