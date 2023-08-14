package com.kotlin.learn.feature.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStorePreferences
) : ViewModel() {

    fun fetchUserFromDatastore() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    dataStore.getString(
                        PreferenceConstants.Authorization.PREF_USER,
                    ).getOrNull().orEmpty()
                )
            )
        }

}