package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.AuthReqModel
import com.kotlin.learn.core.model.AuthRespModel
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel

interface AuthDataSource {

    suspend fun postAuth(authReqModel: AuthReqModel) : AuthRespModel

    suspend fun postRegister(registerReqModel: RegisterReqModel): RegisterRespModel

}