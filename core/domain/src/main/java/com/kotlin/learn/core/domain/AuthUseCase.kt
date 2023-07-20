package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun postAuthorization(model: AuthGoogleSignInModel): Flow<Result<Unit>>

    fun <Z : Any> getAuthorization(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

}