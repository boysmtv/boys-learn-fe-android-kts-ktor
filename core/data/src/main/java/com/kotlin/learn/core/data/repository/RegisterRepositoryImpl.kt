package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.execute
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.network.source.RegisterDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val network: RegisterDataSource
) : RegisterRepository {

    override fun postRegister(model: RegisterReqModel): Flow<Result<BaseResponse<RegisterRespModel>>> = flow {
        emit(execute {
            network.postRegister(model = model)
        })
    }.flowOn(Dispatchers.IO)

}