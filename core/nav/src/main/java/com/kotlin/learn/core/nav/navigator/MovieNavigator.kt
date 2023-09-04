package com.kotlin.learn.core.nav.navigator

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.utilities.MovieCategories
import com.kotlin.learn.feature.movie.presentation.ui.DetailFragmentDirections
import com.kotlin.learn.feature.movie.presentation.ui.HomeFragmentDirections
import com.kotlin.learn.feature.movie.presentation.ui.SearchFragmentDirections
import com.kotlin.learn.feature.movie.presentation.ui.SeeAllMovieFragmentDirections

class MovieNavigator {

    fun fromHomeToDetailMovie(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.id.toString())
        )
    }

    fun fromHomeToSeeAllMovie(fragment: Fragment, categories: MovieCategories) {
        findNavController(fragment).navigate(
            HomeFragmentDirections.actionHomeFragmentToSeeAllMovieFragment(categories.name)
        )
    }

    fun fromSeeAllMovieToDetailMovie(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).navigate(
            SeeAllMovieFragmentDirections.actionSeeAllMovieFragmentToDetailFragment(item.id.toString())
        )
    }

    fun fromSearchToDetail(fragment: Fragment, item: MovieDataModel) {
        findNavController(fragment).navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(item.id.toString())
        )
    }

    fun fromDetailToVideos(fragment: Fragment, movieKey: String) {
        findNavController(fragment).navigate(
            DetailFragmentDirections.actionDetailFragmentToVideoFragment(movieKey)
        )
    }

    fun fromDetailToSeeAllCredits(fragment: Fragment, movieId: String) {
        findNavController(fragment).navigate(
            DetailFragmentDirections.actionDetailFragmentToSeeAllCreditsFragment(movieId)
        )
    }

}