package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.execute
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val network: UserDataSource
) : UserRepository {

    // TODO : start region to spring backend
    // ===============================================================

    override fun getUser(model: UserModel): Flow<Result<BaseResponse<UserModel>>> =
        flow {
            emit(Result.Loading)
            emit(
                execute {
                    network.getUser(model = model)
                }
            )
        }

    override fun postUser(model: UserModel): Flow<Result<BaseResponse<RegisterRespModel>>> =
        flow {
            emit(Result.Loading)
            emit(
                execute {
                    network.postUser(model = model)
                }
            )
        }

    override fun putUser(model: UserModel): Flow<Result<BaseResponse<RegisterRespModel>>> =
        flow {
            emit(Result.Loading)
            emit(
                execute {
                    network.putUser(model = model)
                }
            )
        }

    override fun postAuth(model: UserModel): Flow<Result<BaseResponse<LoginRespModel>>> =
        flow {
            emit(Result.Loading)
            emit(
                execute {
                    network.postAuth(model = model)
                }
            )
        }


    // TODO : start region to firebase
    // ===============================================================

    override fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) = flow {
        emit(
            execute {
                network.storeUserToFirebase(model, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.fetchUserFromFirebase(id, resources, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)


    // TODO : start region to firestore
    // ===============================================================

    override fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.storeUserToFirestore(id, model, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun updateUserToFirestore(
        id: String,
        model: Map<String, String>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.updateUserToFirestore(id, model, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.fetchUserFromFirestore(filter, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)



}