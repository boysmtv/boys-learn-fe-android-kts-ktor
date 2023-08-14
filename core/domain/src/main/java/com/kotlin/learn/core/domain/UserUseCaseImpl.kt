package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.data.repository.UserRepository
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : UserUseCase {
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

    // start region to firestore

    override fun storeUserToFirestore(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>> {
        return repository.storeUserToFirestore(model, onSuccess, onError)
    }

    override fun <Z : Any> fetchUserFromFirestore(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.fetchUserFromFirestore(id, resources, onSuccess, onError)
    }

}