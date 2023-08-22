package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.data.repository.UserRepository
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : UserUseCase {

    // TODO : start region to spring backend
    // ===============================================================

    override fun getUser(model: UserModel): Flow<Result<BaseResponse<UserModel>>> {
        return repository.getUser(model = model)
    }

    override fun postUser(model: UserModel): Flow<Result<BaseResponse<RegisterRespModel>>> {
        return repository.postUser(model = model)
    }

    override fun putUser(model: UserModel): Flow<Result<BaseResponse<RegisterRespModel>>> {
        return repository.putUser(model = model)
    }

    override fun postAuth(model: UserModel): Flow<Result<BaseResponse<LoginRespModel>>> {
        return repository.postAuth(model = model)
    }

    // TODO : start region to firebase
    // ===============================================================

    override fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>> {
        return repository.storeUserToFirebase(model, onSuccess, onError)
    }

    override fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.fetchUserFromFirebase(id, resources, onSuccess, onError)
    }


    // TODO : start region to firestore
    // ===============================================================

    override fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.storeUserToFirestore(id, model, onLoad, onSuccess, onError)
    }

    override fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.fetchUserFromFirestore(filter, onLoad, onSuccess, onError)
    }

}