package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.model.BaseError
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.network.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    fun postRegister(model: RegisterReqModel) : Flow<Result<BaseResponse<RegisterRespModel>>>

    fun postRegisterWithError(model: RegisterReqModel) : Flow<Result<ApiResponse<RegisterRespModel, BaseError>>>

}