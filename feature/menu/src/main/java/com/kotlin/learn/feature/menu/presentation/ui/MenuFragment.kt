package com.kotlin.learn.feature.menu.presentation.ui

import android.annotation.SuppressLint
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.nav.navigator.ParentNavigator
import com.kotlin.learn.feature.menu.R
import com.kotlin.learn.feature.menu.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {

    private val tag = this::class.java.simpleName

    @Inject
    lateinit var parentNavigator: ParentNavigator

    override fun setupView() {
        setupInit()
    }

    @SuppressLint("CommitTransaction")
    private fun setupInit() {
        val fragment = childFragmentManager.findFragmentById(R.id.nav_host_menu_fragment_container) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(fragment.navController)
    }

}
