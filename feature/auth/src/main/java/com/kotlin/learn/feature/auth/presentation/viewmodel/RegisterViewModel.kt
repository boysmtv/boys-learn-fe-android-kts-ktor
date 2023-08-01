package com.kotlin.learn.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.domain.AuthUseCase
import com.kotlin.learn.core.domain.RegisterUseCase
import com.kotlin.learn.core.model.ApiResponse
import com.kotlin.learn.core.model.BaseError
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
) : ViewModel() {

    private val _register: MutableStateFlow<Result<BaseResponse<RegisterRespModel>>> = MutableStateFlow(Result.Loading)
    val register = _register.asStateFlow()

    private val _registerWithError: MutableStateFlow<Result<ApiResponse<RegisterRespModel, BaseError>>> = MutableStateFlow(Result.Loading)
    val registerWithError = _registerWithError.asStateFlow()

    fun postRegister(model: RegisterReqModel) {
        useCase.postRegister(model)
            .onEach { _register.value = it }
            .launchIn(viewModelScope)
    }

    fun postRegisterWithError(model: RegisterReqModel) {
        useCase.postRegisterWithError(model)
            .onEach { _registerWithError.value = it }
            .launchIn(viewModelScope)
    }

}