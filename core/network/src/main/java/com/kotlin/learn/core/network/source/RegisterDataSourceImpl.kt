package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.common.util.network.ResultSpring
import com.kotlin.learn.core.common.util.network.executeSpring
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.network.ApiAuthResources
import com.kotlin.learn.core.network.KtorClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
) : RegisterDataSource {

    override suspend fun postRegister(model: RegisterReqModel): BaseResponse<RegisterRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponse(
                resources = ApiAuthResources.REGISTER,
                body = model
            )
        }
    }
}