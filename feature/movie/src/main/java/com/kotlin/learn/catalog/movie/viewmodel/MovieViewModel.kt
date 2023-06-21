package com.kotlin.learn.catalog.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlin.learn.catalog.core.domain.MovieUseCaseImpl
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    movieUseCaseImpl: MovieUseCaseImpl
) : ViewModel() {

//    val popularMovies: Flow<PagingData<MovieDataModel>> = movieUseCaseImpl.invoke(MovieCategories.POPULAR)
//        .cachedIn(viewModelScope)
//
//    val topRatedMovies: Flow<PagingData<MovieDataModel>> = movieUseCaseImpl.invoke(MovieCategories.TOP_RATED)
//        .cachedIn(viewModelScope)
//
//    val upComingMovies: Flow<PagingData<MovieDataModel>> = movieUseCaseImpl.invoke(MovieCategories.UP_COMING)
//        .cachedIn(viewModelScope)

}