package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun postAuthorization(
        model: AuthGoogleSignInModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>>

    fun <Z : Any> getAuthorization(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun postLogin(model: LoginReqModel): Flow<Result<BaseResponse<LoginRespModel>>>

}