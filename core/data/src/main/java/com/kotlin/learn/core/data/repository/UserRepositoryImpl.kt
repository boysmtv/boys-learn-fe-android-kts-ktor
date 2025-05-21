package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.data.db.dao.UserDao
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.common.util.network.execute
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginModel
import com.kotlin.learn.core.model.RegisterModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val network: UserDataSource,
    private val userDao: UserDao
) : UserRepository {

    // start region to spring backend
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

    override fun postUser(model: UserModel): Flow<Result<BaseResponse<RegisterModel>>> =
        flow {
            emit(Result.Loading)
            emit(
                execute {
                    network.postUser(model = model)
                }
            )
        }

    override fun putUser(model: UserModel): Flow<Result<BaseResponse<RegisterModel>>> =
        flow {
            emit(Result.Loading)
            emit(
                execute {
                    network.putUser(model = model)
                }
            )
        }

    override fun postAuth(model: UserModel): Flow<Result<BaseResponse<LoginModel>>> =
        flow {
            emit(Result.Loading)
            emit(
                execute {
                    network.postAuth(model = model)
                }
            )
        }


    // start region to firebase
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


    // start region to firestore
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

    override fun <T> updateUserToFirestore(
        id: String,
        model: Map<String, T>,
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

    override fun <T : Any> fetchUserFromFirestore(
        filter: Pair<String, String>,
        resources: T
    ): Flow<ResultCallback<T>> = network.fetchUserFromFirestore(filter, resources)

    override fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>
    ): Flow<ResultCallback<String>> = network.updateUserToFirestoreAsync(id, model)

}