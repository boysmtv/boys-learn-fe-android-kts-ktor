package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.data.repository.RegisterRepository
import com.kotlin.learn.core.model.ApiResponse
import com.kotlin.learn.core.model.BaseError
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: RegisterRepository
) : RegisterUseCase {

    override fun postRegister(model: RegisterReqModel): Flow<Result<BaseResponse<RegisterRespModel>>> {
        return repository.postRegister(model = model)
    }

    override fun postRegisterWithError(model: RegisterReqModel): Flow<Result<ApiResponse<RegisterRespModel, BaseError>>> {
        return repository.postRegisterWithError(model = model)
    }

}