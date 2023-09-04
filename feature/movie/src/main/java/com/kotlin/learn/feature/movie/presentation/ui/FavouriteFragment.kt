package com.kotlin.learn.feature.movie.presentation.ui

import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.nav.navigator.MovieNavigator
import com.kotlin.learn.feature.movie.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate) {

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun setupView() {

    }

}