package com.kotlin.learn.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.data.repository.PreferencesRepository
import com.kotlin.learn.core.domain.AuthUseCase
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    fun fetchDataAuth() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    preferencesRepository.getString(
                        PreferenceConstants.Authorization.PREF_GOOGLE_AUTH
                    ).getOrNull().orEmpty()
                )
            )
        }

    fun fetchDataTokenFcm() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    preferencesRepository.getString(
                        PreferenceConstants.Authorization.PREF_FCM_TOKEN
                    ).getOrNull().orEmpty()
                )
            )
        }

}