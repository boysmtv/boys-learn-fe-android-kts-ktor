package com.kotlin.learn.catalog.core.nav.navigator

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import com.kotlin.learn.catalog.movie.presentation.ui.HomeFragmentDirections

class MovieNavigator {

    fun navigateToDetailMovie(fragment: Fragment, item: MovieDataModel, categories: MovieCategories) {
        findNavController(fragment).navigate(
            HomeFragmentDirections.actionHomeFragmentToViewerFragment(item.id.toString()    )
        )
    }

}