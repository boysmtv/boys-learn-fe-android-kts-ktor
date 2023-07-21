package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.network.firebase.FirebaseClient
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseClient: FirebaseClient
) : AuthDataSource {

    override suspend fun postAuthorization(model: AuthGoogleSignInModel) {
        firebaseClient.postFirebaseRequest(model)
    }

    override suspend fun <Z : Any> getAuthorization(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) = firebaseClient.getFirebaseRequest(id, resources, onSuccess, onError)

}