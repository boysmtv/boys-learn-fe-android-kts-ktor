package com.kotlin.learn.feature.auth.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.feature.auth.databinding.FragmentGreetingsBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.GreetingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingsFragment : BaseFragment<FragmentGreetingsBinding>(FragmentGreetingsBinding::inflate) {

    private val viewModel: GreetingsViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    override fun setupView() {

    }

    private fun setupListener() = with(binding) {
        btnEmail.setOnClickListener {
            authNavigator.fromGreetingsToAuth(this@GreetingsFragment)
        }
    }

}