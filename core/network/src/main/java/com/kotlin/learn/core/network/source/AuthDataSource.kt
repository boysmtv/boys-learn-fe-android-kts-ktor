package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.AuthGoogleSignInModel

interface AuthDataSource {

    suspend fun postAuthorization(
        model: AuthGoogleSignInModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    )

    suspend fun <Z : Any> getAuthorization(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    )

}