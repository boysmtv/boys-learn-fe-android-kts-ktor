package com.kotlin.learn.catalog.core.domain

import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.model.AuthReqModel
import com.kotlin.learn.catalog.core.model.AuthRespModel
import com.kotlin.learn.catalog.core.model.RegisterReqModel
import com.kotlin.learn.catalog.core.model.RegisterRespModel
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun postAuth(authReqModel: AuthReqModel): Flow<Result<AuthRespModel>>

    fun postRegister(registerReqModel: RegisterReqModel): Flow<Result<RegisterRespModel>>

}