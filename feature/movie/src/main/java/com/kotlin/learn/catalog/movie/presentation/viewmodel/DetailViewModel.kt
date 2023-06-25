package com.kotlin.learn.catalog.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.domain.MovieUseCase
import com.kotlin.learn.catalog.core.model.MovieDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    private val _detailMovies: MutableStateFlow<Result<MovieDetailModel>> = MutableStateFlow(Result.Loading)
    val detailMovies =_detailMovies.asStateFlow()

    fun getDetailMovies(movieId: String){
        movieUseCase.getDetailMovie(movieId = movieId).onEach {
            _detailMovies.value = it
        }.launchIn(viewModelScope)
    }

}