package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.ApiAuthResources
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.network.firebase.FirebaseClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
    private val firebaseClient: FirebaseClient,
) : UserDataSource {
    override suspend fun getUser(model: UserModel): BaseResponse<UserModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponseFromSpring(
                resources = ApiAuthResources.PROFILE,
                body = model
            )
        }
    }

    override suspend fun postUser(model: UserModel): BaseResponse<RegisterRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponseFromSpring(
                resources = ApiAuthResources.REGISTER,
                body = model
            )
        }
    }

    override suspend fun putUser(model: UserModel): BaseResponse<RegisterRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.putAPIwithResponseFromSpring(
                resources = ApiAuthResources.PROFILE,
                body = model
            )
        }
    }

    override suspend fun postAuth(model: UserModel): BaseResponse<LoginRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponseFromSpring(
                resources = ApiAuthResources.LOGIN,
                body = model
            )
        }
    }

    // start region to firestore

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
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ) = firebaseClient.fetchRequestFromFirestore(id, resources, onSuccess, onError)
}