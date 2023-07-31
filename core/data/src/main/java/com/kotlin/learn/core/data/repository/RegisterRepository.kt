package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    fun postRegister(model: RegisterReqModel) : Flow<Result<RegisterRespModel>>

}