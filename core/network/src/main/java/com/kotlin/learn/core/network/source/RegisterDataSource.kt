package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel

interface RegisterDataSource {

    suspend fun postRegister(model: RegisterReqModel): RegisterRespModel

}