package com.kotlin.learn.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.domain.MovieUseCase
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.model.VideoDetailModel
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

    private val _detailVideos: MutableStateFlow<Result<VideoDetailModel>> = MutableStateFlow(Result.Loading)
    val detailVideos =_detailVideos.asStateFlow()

    fun getDetailMovies(movieId: String){
        movieUseCase.getDetailMovie(movieId = movieId).onEach {
            _detailMovies.value = it
        }.launchIn(viewModelScope)
    }

    fun getDetailVideos(movieId: String){
        movieUseCase.getDetailVideo(movieId = movieId).onEach {
            _detailVideos.value = it
        }.launchIn(viewModelScope)
    }

}