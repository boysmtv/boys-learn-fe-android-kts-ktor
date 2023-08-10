package com.kotlin.learn.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.data.repository.PreferencesRepository
import com.kotlin.learn.core.domain.AuthUseCase
import com.kotlin.learn.core.domain.MovieUseCase
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.model.MovieModel
import com.kotlin.learn.core.utilities.MovieCategories
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: AuthUseCase,
    private val movieUseCase: MovieUseCase,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    val popularMovies: Flow<PagingData<MovieDataModel>> = movieUseCase.getMovie(MovieCategories.POPULAR)
        .cachedIn(viewModelScope)

    val topRatedMovies: Flow<PagingData<MovieDataModel>> = movieUseCase.getMovie(MovieCategories.TOP_RATED)
        .cachedIn(viewModelScope)

    val upComingMovies: Flow<PagingData<MovieDataModel>> = movieUseCase.getMovie(MovieCategories.UP_COMING)
        .cachedIn(viewModelScope)

    private val _nowPlayingMovies: MutableStateFlow<Result<MovieModel>> = MutableStateFlow(Result.Loading)
    val nowPlayingMovies = _nowPlayingMovies.asStateFlow()

    init {
        nowPlayingMovies()
    }

    fun nowPlayingMovies() {
        movieUseCase.getBanner(1)
            .onEach { _nowPlayingMovies.value = it }
            .launchIn(viewModelScope)
    }

    fun fetchAuthFromDataStore() = flow {
        emit(
            DataStoreCacheEvent.FetchSuccess(
                preferencesRepository.getString(
                    PreferenceConstants.Authorization.PREF_GOOGLE_AUTH
                ).getOrNull().orEmpty()
            )
        )
    }

    fun <Z : Any> fetchAuthDataFromFirebase(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) = useCase.getAuthorization(
        id,
        resources,
        onSuccess,
        onError
    ).launchIn(viewModelScope)

}