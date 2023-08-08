package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.data.repository.AuthRepository
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override fun postAuthorization(
        model: AuthGoogleSignInModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>> {
        return authRepository.postAuthorization(model, onSuccess, onError)
    }

    override fun <Z : Any> getAuthorization(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return authRepository.getAuthorization(id, resources, onSuccess, onError)
    }

    override fun postLogin(model: LoginReqModel): Flow<Result<BaseResponse<LoginRespModel>>> {
        return authRepository.postLogin(model = model)
    }

}