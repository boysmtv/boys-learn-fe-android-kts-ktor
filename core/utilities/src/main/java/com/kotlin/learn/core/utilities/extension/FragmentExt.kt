package com.kotlin.learn.core.utilities.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

fun Fragment.replaceFragment(
    container: FragmentContainerView,
    fragment: Fragment
) {
    childFragmentManager.beginTransaction()
        .replace(container.id, fragment)
        .commit()
}