package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun storeUserToFirestore(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>>

    fun <Z : Any> fetchUserFromFirestore(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun postAuthorization(model: LoginReqModel): Flow<Result<BaseResponse<LoginRespModel>>>

}