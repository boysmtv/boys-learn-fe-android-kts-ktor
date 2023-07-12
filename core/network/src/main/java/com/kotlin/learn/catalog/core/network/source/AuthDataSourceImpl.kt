package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.model.AuthReqModel
import com.kotlin.learn.catalog.core.model.AuthRespModel
import com.kotlin.learn.catalog.core.model.RegisterReqModel
import com.kotlin.learn.catalog.core.model.RegisterRespModel
import com.kotlin.learn.catalog.core.network.Authentication
import com.kotlin.learn.catalog.core.network.KtorClient
import com.kotlin.learn.catalog.core.network.Register
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient
) : AuthDataSource {

    override suspend fun postAuth(authReqModel: AuthReqModel): AuthRespModel {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = Authentication(),
                query = emptyMap()
            )
        }
    }

    override suspend fun postRegister(registerReqModel: RegisterReqModel): RegisterRespModel {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = Register(),
                query = emptyMap()
            )
        }
    }

}