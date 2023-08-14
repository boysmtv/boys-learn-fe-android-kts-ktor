package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserUseCase {

    fun getUser(model: UserModel): Flow<Result<BaseResponse<UserModel>>>

    fun postUser(model: UserModel): Flow<Result<BaseResponse<RegisterRespModel>>>

    fun putUser(model: UserModel): Flow<Result<BaseResponse<RegisterRespModel>>>

    fun postAuth(model: UserModel): Flow<Result<BaseResponse<LoginRespModel>>>

    // start region to firestore

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

}
