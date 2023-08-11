package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.execute
import com.kotlin.learn.core.model.UserModel
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

    override fun storeUserToFirestore(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) = flow {
        emit(
            execute {
                network.storeUserToFirestore(model, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun <Z : Any> fetchUserFromFirestore(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.fetchUserFromFirestore(id, resources, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun postAuthorization(model: LoginReqModel): Flow<Result<BaseResponse<LoginRespModel>>> =
        flow {
            emit(
                execute {
                    network.postAuthorization(model = model)
                }
            )
        }

}