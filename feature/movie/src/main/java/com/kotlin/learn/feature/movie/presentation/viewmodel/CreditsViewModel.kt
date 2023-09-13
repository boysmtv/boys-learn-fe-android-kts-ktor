package com.kotlin.learn.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.domain.MovieUseCase
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.utilities.extension.runSafeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreditsViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private val _creditsMovie: MutableStateFlow<Result<CreditsModel>> = MutableStateFlow(Result.Loading)
    val creditsMovie = _creditsMovie.asStateFlow()

    fun getCredits(movieId: String) {
        useCase.getCredits(movieId = movieId).onEach {
            _creditsMovie.value = it
        }.launchIn(viewModelScope)
    }

}