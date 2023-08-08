package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.execute
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.network.source.AuthDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val network: AuthDataSource
) : AuthRepository {

    override fun postAuthorization(
        model: AuthGoogleSignInModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) = flow {
        emit(
            execute {
                network.postAuthorization(model, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun <Z : Any> getAuthorization(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.getAuthorization(id, resources, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun postLogin(model: LoginReqModel): Flow<Result<BaseResponse<LoginRespModel>>> =
        flow {
            emit(
                execute {
                    network.postLogin(model = model)
                }
            )
        }

}