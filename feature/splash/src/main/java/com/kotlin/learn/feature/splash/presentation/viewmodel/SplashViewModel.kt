package com.kotlin.learn.feature.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.event.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.domain.ProfileUseCase
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.model.ProfileModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: ProfileUseCase,
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil
) : ViewModel() {

    // TODO : start region to datastore
    // ===============================================================

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

    fun storeProfileToDatastore(data: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_PROFILE,
                data
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

    fun storeDeviceIdToFirestore(data: String) {
        viewModelScope.launch {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_ID_DEVICE,
                data
            )
        }
    }


    // TODO : start region to firestore
    // ===============================================================

    fun <T : Any> storeProfileToFirestore(
        id: String,
        model: T,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        useCase.storeProfileToFirestore(
            id = id,
            model = model,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        ).launchIn(viewModelScope)
    }

    fun updateProfileToFirestore(
        id: String,
        model: Map<String, String>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        useCase.updateProfileToFirestore(
            id = id,
            model = model,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        ).launchIn(viewModelScope)
    }

    private val _profile: MutableStateFlow<ResultCallback<ProfileModel>> = MutableStateFlow(ResultCallback.Loading)
    val profile = _profile.asStateFlow()

    fun fetchProfileFromFirestore(
        filter: Pair<String, String>,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.fetchProfileFromFirestore(
                    filter = filter,
                    resources = ProfileModel(),
                ).collect {
                    _profile.value = it
                }
            }
        }

    }

}