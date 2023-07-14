package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.model.AuthReqModel
import com.kotlin.learn.core.model.AuthRespModel
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun postAuth(authReqModel: AuthReqModel) : Flow<Result<AuthRespModel>>

    fun postRegister(registerReqModel: RegisterReqModel): Flow<Result<RegisterRespModel>>

}