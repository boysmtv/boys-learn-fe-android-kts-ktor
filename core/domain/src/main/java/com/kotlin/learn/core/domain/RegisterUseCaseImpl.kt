package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.ResultSpring
import com.kotlin.learn.core.data.repository.RegisterRepository
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: RegisterRepository
) : RegisterUseCase {

    override suspend fun postRegister(model: RegisterReqModel): Flow<ResultSpring<BaseResponse<RegisterRespModel>>> {
        return repository.postRegister(model = model)
    }

}