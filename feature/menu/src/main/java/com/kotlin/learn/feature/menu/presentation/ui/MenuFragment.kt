package com.kotlin.learn.feature.menu.presentation.ui

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

    private fun setupInit() {
        val host = childFragmentManager.findFragmentById(R.id.nav_host_menu_fragment_container) as NavHostFragment
        val controller = host.navController
        binding.bottomNavigationView.setupWithNavController(controller)
    }

}