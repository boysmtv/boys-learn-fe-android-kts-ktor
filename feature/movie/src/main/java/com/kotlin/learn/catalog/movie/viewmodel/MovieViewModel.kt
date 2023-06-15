package com.kotlin.learn.catalog.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.kotlin.learn.catalog.core.domain.MovieUseCase
import com.kotlin.learn.catalog.core.model.NetworkMovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    movieUseCase : MovieUseCase
) : ViewModel() {

    val popularMovies: Flow<PagingData<NetworkMovieData>> = movieUseCase.invoke()

    val topRatedMovies: Flow<PagingData<NetworkMovieData>> = movieUseCase.invoke()

    val mustWatchedMovies: Flow<PagingData<NetworkMovieData>> = movieUseCase.invoke()

}