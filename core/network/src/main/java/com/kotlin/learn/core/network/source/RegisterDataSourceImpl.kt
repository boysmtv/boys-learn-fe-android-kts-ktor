package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.network.Register
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
) : RegisterDataSource {

    override suspend fun postRegister(model: RegisterReqModel): RegisterRespModel {
        return withContext(Dispatchers.IO){
            ktorClient.sendRequestApiWithQuery(
                resources = Register(),
                query = emptyMap(),
                body = model
            )
        }
    }

}