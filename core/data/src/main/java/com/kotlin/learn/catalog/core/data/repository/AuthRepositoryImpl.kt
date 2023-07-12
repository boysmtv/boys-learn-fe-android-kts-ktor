package com.kotlin.learn.catalog.core.data.repository

import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.common.executeWithResponse
import com.kotlin.learn.catalog.core.model.AuthReqModel
import com.kotlin.learn.catalog.core.model.AuthRespModel
import com.kotlin.learn.catalog.core.model.RegisterReqModel
import com.kotlin.learn.catalog.core.model.RegisterRespModel
import com.kotlin.learn.catalog.core.network.source.AuthDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val network: AuthDataSource
) : AuthRepository {

    override fun postAuth(authReqModel: AuthReqModel): Flow<Result<AuthRespModel>> = flow {
        emit(
            executeWithResponse {
                network.postAuth(authReqModel)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun postRegister(registerReqModel: RegisterReqModel): Flow<Result<RegisterRespModel>> = flow {
        emit(
            executeWithResponse {
                network.postRegister(registerReqModel)
            }
        )
    }.flowOn(Dispatchers.IO)

}