package com.kotlin.learn.feature.movie.presentation.ui

import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.nav.navigator.MovieNavigator
import com.kotlin.learn.core.utilities.MovieCategories
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.databinding.FragmentFavouriteBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate) {

    private val viewModel: FavouriteViewModel by viewModels()

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun setupView() {
        subscribeMovie()
        setupAdapterMovie()
    }

    private fun subscribeMovie() = with(viewModel) {
        popularMovies.launch(this@FavouriteFragment) { seeAllMovieAdapter.submitData(it) }
    }

}