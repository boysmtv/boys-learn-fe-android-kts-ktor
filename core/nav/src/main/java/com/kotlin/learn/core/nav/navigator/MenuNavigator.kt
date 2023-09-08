package com.kotlin.learn.core.nav.navigator

import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.kotlin.learn.core.nav.R
import com.kotlin.learn.feature.menu.presentation.ui.MenuFragmentDirections

class MenuNavigator {

    private fun initNavHostFragment(activity: FragmentActivity): NavHostFragment {
        return activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
    }

    fun fromMenuToGreetings(activity: FragmentActivity) {
        initNavHostFragment(activity).findNavController().navigate(
            MenuFragmentDirections.actionMenuFragmentToGreetingsFragment()
        )
    }

}