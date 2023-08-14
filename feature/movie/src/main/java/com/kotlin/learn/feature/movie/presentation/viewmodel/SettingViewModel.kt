package com.kotlin.learn.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val dataStore: DataStorePreferences
) : ViewModel() {

    fun fetchDataAuth() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    dataStore.getString(
                        PreferenceConstants.Authorization.PREF_USER
                    ).getOrNull().orEmpty()
                )
            )
        }

    fun fetchDataTokenFcm() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    dataStore.getString(
                        PreferenceConstants.Authorization.PREF_FCM_TOKEN
                    ).getOrNull().orEmpty()
                )
            )
        }

    fun storeTokenToDataStore(token: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                token
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

    fun clearPreferences(token: String) {
        viewModelScope.launch {
            dataStore.clearPreferences()
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                token
            )
        }
    }

}