package com.kotlin.learn.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.event.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.data.preferences.DataStorePreferences
import com.kotlin.learn.core.domain.MovieUseCase
import com.kotlin.learn.core.domain.UserUseCase
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.model.VideoDetailModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val userUseCase: UserUseCase,
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil
) : ViewModel() {

    private val _detailMovies: MutableStateFlow<Result<MovieDetailModel>> = MutableStateFlow(Result.Loading)
    val detailMovies = _detailMovies.asStateFlow()

    private val _detailVideos: MutableStateFlow<Result<VideoDetailModel>> = MutableStateFlow(Result.Loading)
    val detailVideos = _detailVideos.asStateFlow()

    fun getDetailMovies(movieId: String) {
        movieUseCase.getDetailMovie(movieId = movieId).onEach {
            _detailMovies.value = it
        }.launchIn(viewModelScope)
    }

    fun getDetailVideos(movieId: String) {
        movieUseCase.getDetailVideo(movieId = movieId).onEach {
            _detailVideos.value = it
        }.launchIn(viewModelScope)
    }

    // TODO : start region to datastore
    // ===============================================================

    fun storeUserToDatastore(user: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_USER,
                user
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

    fun fetchUserFromDatastore() =
        flow {
            val data = dataStore.getString(PreferenceConstants.Authorization.PREF_USER).getOrNull().orEmpty()
            if (data.isNotEmpty() && data.isNotBlank()) {
                emit(
                    DataStoreCacheEvent.FetchSuccess(
                        jsonUtil.fromJson<UserModel>(data)
                    )
                )
            } else {
                emit(DataStoreCacheEvent.FetchError)
            }
        }

    // TODO : start region to firestore
    // ===============================================================

    fun <T> updateUserToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        userUseCase.updateUserToFirestore(
            id = id,
            filter = model,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        ).launchIn(viewModelScope)
    }
}