package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.network.firebase.FirebaseClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseClient: FirebaseClient
) : AuthDataSource {

    override suspend fun postAuthorization(model: AuthGoogleSignInModel) {
        return withContext(Dispatchers.IO) {
            firebaseClient.postFirebaseRequest(model)
        }
    }

    override suspend fun <Z> getAuthorization(
        id: String,
        resources: Any,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ) {
        return firebaseClient.getFirebaseRequest(id, resources, onSuccess, onError)
    }


}