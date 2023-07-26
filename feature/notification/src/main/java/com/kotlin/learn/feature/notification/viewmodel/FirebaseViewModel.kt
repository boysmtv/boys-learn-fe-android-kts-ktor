package com.kotlin.learn.feature.notification.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.data.repository.PreferencesRepository
import com.kotlin.learn.core.utilities.PreferenceConstants
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    fun storeTokenFirebase(token: String) =
        flow {
            preferencesRepository.setString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                token
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

}