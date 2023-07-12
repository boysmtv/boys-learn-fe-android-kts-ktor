package com.kotlin.learn.catalog.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.domain.AuthUseCase
import com.kotlin.learn.catalog.core.model.RegisterReqModel
import com.kotlin.learn.catalog.core.model.RegisterRespModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : ViewModel() {

    private val _register: MutableStateFlow<Result<RegisterRespModel>> = MutableStateFlow(Result.Loading)
    val register = _register.asStateFlow()

    fun postRegister(registerReqModel: RegisterReqModel) {
        useCase.postRegister(registerReqModel)
            .onEach { _register.value = it }
            .launchIn(viewModelScope)
    }

}