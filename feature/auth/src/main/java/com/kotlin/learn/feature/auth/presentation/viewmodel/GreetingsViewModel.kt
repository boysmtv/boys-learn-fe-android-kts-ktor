package com.kotlin.learn.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.data.repository.DataStorePreferences
import com.kotlin.learn.core.domain.AuthUseCase
import com.kotlin.learn.core.model.UserModel
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
    private val dataStore: DataStorePreferences
) : ViewModel() {

    private val _storeFirebase: MutableStateFlow<Result<Unit>> = MutableStateFlow(Result.Waiting)
    val storeFirebase = _storeFirebase.asStateFlow()

    //Notes: Store User Model to Datastore
    fun storeUserToDatastore(user: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_USER,
                user
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

    //Notes: Store User Model to Firestore
    fun storeUserToFirestore(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        useCase.storeUserToFirestore(model, onSuccess, onError)
            .onEach { _storeFirebase.value = it }
            .launchIn(viewModelScope)
    }

}