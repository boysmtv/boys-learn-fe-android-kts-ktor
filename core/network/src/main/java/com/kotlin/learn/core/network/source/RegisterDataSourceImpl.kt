package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.ApiResponse
import com.kotlin.learn.core.model.BaseError
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterReqModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.network.ApiAuthResources
import com.kotlin.learn.core.network.KtorClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
) : RegisterDataSource {

    override suspend fun postRegister(model: RegisterReqModel): BaseResponse<RegisterRespModel> {
        return withContext(Dispatchers.IO){
            ktorClient.postRequestApi(
                resources = ApiAuthResources.REGISTER,
                body = model
            )
        }
    }

    override suspend fun postRegisterWithError(model: RegisterReqModel): ApiResponse<RegisterRespModel, BaseError> {
        return withContext(Dispatchers.IO){
            ktorClient.postRequestApis(
                resources = ApiAuthResources.REGISTER,
                body = model
            )
        }
    }

}