package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.data.repository.AuthRepository
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override fun postAuthorization(model: AuthGoogleSignInModel): Flow<Result<Unit>> {
        return authRepository.postAuthorization(model)
    }

    override fun <Z : Any> getAuthorization(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return authRepository.getAuthorization(id, resources, onSuccess, onError)
    }

}