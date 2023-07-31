package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.data.repository.RegisterRepository
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: RegisterRepository
) : RegisterUseCase {

    override fun postRegister(model: RegisterReqModel): Flow<Result<RegisterRespModel>> {
        return repository.postRegister(model = model)
    }

}