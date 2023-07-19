package com.kotlin.learn.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.data.repository.PreferencesRepository
import com.kotlin.learn.core.domain.AuthUseCase
import com.kotlin.learn.core.model.AuthReqModel
import com.kotlin.learn.core.model.AuthRespModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GreetingsViewModel @Inject constructor(
    private val useCase: AuthUseCase,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _postAuth: MutableStateFlow<Result<AuthRespModel>> = MutableStateFlow(Result.Loading)
    val postAuth = _postAuth.asStateFlow()

    fun postAuth(authReqModel: AuthReqModel) {
        useCase.postAuth(authReqModel)
            .onEach { _postAuth.value = it }
            .launchIn(viewModelScope)
    }

    fun fetchDataAuth() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    preferencesRepository.getString(PreferenceConstants.Authorization.PREF_GOOGLE_AUTH).getOrNull()
                        .orEmpty()
                )
            )
        }

    fun storeDataAuth(auth: String) =
        flow {
            preferencesRepository.setString(PreferenceConstants.Authorization.PREF_GOOGLE_AUTH, auth)
            emit(DataStoreCacheEvent.StoreSuccess)
        }

}