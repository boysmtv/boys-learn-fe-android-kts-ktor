package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.executeWithResponse
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.network.source.AuthDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val network: AuthDataSource
) : AuthRepository {

    override fun postAuthorization(model: AuthGoogleSignInModel) = flow {
        emit(
            executeWithResponse {
                network.postAuthorization(model)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun getAuthorization(id: String, resources: Any) = flow {
        emit(
            executeWithResponse {
                network.getAuthorization(id, resources)
            }
        )
    }.flowOn(Dispatchers.IO)

}