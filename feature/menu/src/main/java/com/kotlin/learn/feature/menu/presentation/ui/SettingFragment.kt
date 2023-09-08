package com.kotlin.learn.feature.menu.presentation.ui

import android.widget.Toast
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.feature.menu.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    private var isAutoLogin = true
    private var isSaveFavourite = true
    private var isShowNotification = true

    override fun setupView() {
        setupListener()
    }

    private fun setupListener() = with(binding) {
        swAutoLogin.setOnCheckedChangeListener { _, _ ->
            isAutoLogin = !isAutoLogin
            setupAutoLoginListener()
        }
        swSaveMovie.setOnCheckedChangeListener { _, _ ->
            isSaveFavourite = !isSaveFavourite
            setupSaveMovieListener()
        }
        swNotification.setOnCheckedChangeListener { _, _ ->
            isShowNotification = !isShowNotification
            setupNotificationListener()
        }
    }

    private fun setupAutoLoginListener() {
        if (isAutoLogin)
            Toast.makeText(requireContext(), "Auto Login is Active", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(requireContext(), "Auto Login is Non Active", Toast.LENGTH_SHORT).show()
    }

    private fun setupSaveMovieListener() {
        if (isSaveFavourite)
            Toast.makeText(requireContext(), "Save to Favourite is Active", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(requireContext(), "Save to Favourite is Non Active", Toast.LENGTH_SHORT).show()
    }

    private fun setupNotificationListener() {
        if (isShowNotification)
            Toast.makeText(requireContext(), "Show Notification is Active", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(requireContext(), "Show Notification is Non Active", Toast.LENGTH_SHORT).show()
    }

}