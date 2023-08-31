package com.kotlin.learn.feature.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.learn.core.common.util.event.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.model.ProfileModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil
) : ViewModel() {

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

    fun storeProfileToDatastore(user: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_PROFILE,
                user
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

    fun fetchProfileFromDatastore() =
        flow {
            val data = dataStore.getString(PreferenceConstants.Authorization.PREF_PROFILE).getOrNull().orEmpty()
            if (data.isNotEmpty() && data.isNotBlank()) {
                emit(
                    DataStoreCacheEvent.FetchSuccess(
                        jsonUtil.fromJson<ProfileModel>(data)
                    )
                )
            } else {
                emit(DataStoreCacheEvent.FetchError)
            }
        }

}