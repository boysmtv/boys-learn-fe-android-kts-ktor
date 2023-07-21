package com.kotlin.learn.core.nav.navigator

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.utilities.MovieCategories
import com.kotlin.learn.feature.movie.presentation.ui.HomeFragmentDirections
import com.kotlin.learn.feature.movie.presentation.ui.SearchFragmentDirections
import com.kotlin.learn.feature.movie.presentation.ui.SeeAllFragmentDirections

class MovieNavigator {

    fun navigateToDetailMovie(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.id.toString())
        )
    }

    fun fromHomeToSearch(fragment: Fragment) {
        findNavController(fragment).navigate(
            HomeFragmentDirections.actionHomeFragmentToSearchFragment()
        )
    }

    fun fromHomeToSetting(fragment: Fragment) {
        findNavController(fragment).navigate(
            HomeFragmentDirections.actionHomeFragmentToSettingFragment()
        )
    }

    fun navigateToSeeAllMovie(fragment: Fragment, categories: MovieCategories) {
        findNavController(fragment).navigate(
            HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(categories.name)
        )
    }

    fun fromSeeAllToDetailMovie(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).navigate(
            SeeAllFragmentDirections.actionSeeAllFragmentToDetailFragment(item.id.toString())
        )
    }

    fun fromSearchToDetail(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(item.id.toString())
        )
    }

}