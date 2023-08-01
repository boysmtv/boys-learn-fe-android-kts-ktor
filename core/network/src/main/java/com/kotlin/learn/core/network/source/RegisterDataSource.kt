package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.BaseError
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.network.util.ApiResponse

interface RegisterDataSource {

    suspend fun postRegister(model: RegisterReqModel): BaseResponse<RegisterRespModel>

    suspend fun postRegisterWithError(model: RegisterReqModel): ApiResponse<RegisterRespModel, BaseError>

}