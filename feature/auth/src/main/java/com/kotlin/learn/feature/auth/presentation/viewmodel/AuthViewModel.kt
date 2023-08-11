package com.kotlin.learn.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.domain.AuthUseCase
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : ViewModel() {

    /*
    TODO : start region post login
    */

    private val _postAuthorization: MutableStateFlow<Result<BaseResponse<LoginRespModel>>> =
        MutableStateFlow(Result.Waiting)
    val postAuthorization = _postAuthorization.asStateFlow()

    fun postAuthorization(
        model: LoginReqModel
    ) {
        useCase.postAuthorization(model = model)
            .onEach { _postAuthorization.value = it }
            .launchIn(viewModelScope)
    }

    /*
    TODO : start region store user to firestore
    */

    private val _storeFirestore: MutableStateFlow<Result<Unit>> = MutableStateFlow(Result.Waiting)
    val storeFirestore = _storeFirestore.asStateFlow()

    fun storeUserToFirestore(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        useCase.storeUserToFirestore(model, onSuccess, onError)
            .onEach { _storeFirestore.value = it }
            .launchIn(viewModelScope)
    }

}