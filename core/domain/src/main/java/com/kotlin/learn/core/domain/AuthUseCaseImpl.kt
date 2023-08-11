package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.data.repository.AuthRepository
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override fun storeUserToFirestore(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>> {
        return authRepository.storeUserToFirestore(model, onSuccess, onError)
    }

    override fun <Z : Any> fetchUserFromFirestore(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return authRepository.fetchUserFromFirestore(id, resources, onSuccess, onError)
    }

    override fun postAuthorization(model: LoginReqModel): Flow<Result<BaseResponse<LoginRespModel>>> {
        return authRepository.postAuthorization(model = model)
    }

}