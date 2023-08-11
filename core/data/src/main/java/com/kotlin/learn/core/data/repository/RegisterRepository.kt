package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.ResultSpring
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    fun postRegister(model: UserModel): Flow<Result<BaseResponse<RegisterRespModel>>>

}