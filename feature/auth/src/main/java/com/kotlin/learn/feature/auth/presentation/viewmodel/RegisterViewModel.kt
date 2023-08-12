package com.kotlin.learn.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.data.repository.DataStorePreferences
import com.kotlin.learn.core.domain.RegisterUseCase
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterRespModel
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
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase,
    private val dataStore: DataStorePreferences
) : ViewModel() {

    private val _register: MutableStateFlow<Result<BaseResponse<RegisterRespModel>>> = MutableStateFlow(Result.Waiting)
    val register = _register.asStateFlow()

    fun postRegister(model: UserModel) {
        useCase.postRegister(model)
            .onEach { _register.value = it }
            .launchIn(viewModelScope)
    }

    fun storeUserToDatastore(user: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_USER,
                user
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

}