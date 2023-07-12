package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.model.AuthReqModel
import com.kotlin.learn.catalog.core.model.AuthRespModel
import com.kotlin.learn.catalog.core.model.RegisterReqModel
import com.kotlin.learn.catalog.core.model.RegisterRespModel
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    suspend fun postAuth(authReqModel: AuthReqModel) : AuthRespModel

    suspend fun postRegister(registerReqModel: RegisterReqModel): RegisterRespModel

}