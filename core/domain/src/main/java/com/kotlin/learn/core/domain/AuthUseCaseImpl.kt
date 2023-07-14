package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.data.repository.AuthRepository
import com.kotlin.learn.core.model.AuthReqModel
import com.kotlin.learn.core.model.AuthRespModel
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override fun postAuth(authReqModel: AuthReqModel): Flow<Result<AuthRespModel>> {
        return authRepository.postAuth(authReqModel)
    }

    override fun postRegister(registerReqModel: RegisterReqModel): Flow<Result<RegisterRespModel>> {
        return authRepository.postRegister(registerReqModel)
    }

}