package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginReqModel
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.network.ApiAuthResources
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.network.firebase.FirebaseClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseClient: FirebaseClient,
    private val ktorClient: KtorClient
) : AuthDataSource {

    // Start region firestore

    override suspend fun storeUserToFirestore(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        firebaseClient.storeRequestToFirestore(model, onSuccess, onError)
    }

    override suspend fun <Z : Any> fetchUserFromFirestore(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) = firebaseClient.fetchRequestFromFirestore(id, resources, onSuccess, onError)

    // End region firestore

    override suspend fun postAuthorization(model: LoginReqModel): BaseResponse<LoginRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponseFromSpring(
                resources = ApiAuthResources.LOGIN,
                body = model
            )
        }
    }

}