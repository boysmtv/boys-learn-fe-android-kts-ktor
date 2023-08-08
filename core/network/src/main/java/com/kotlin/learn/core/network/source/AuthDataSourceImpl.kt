package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.AuthGoogleSignInModel
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

    override suspend fun postAuthorization(
        model: AuthGoogleSignInModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        firebaseClient.postFirebaseRequest(model, onSuccess, onError)
    }

    override suspend fun <Z : Any> getAuthorization(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) = firebaseClient.getFirebaseRequest(id, resources, onSuccess, onError)

    override suspend fun postLogin(model: LoginReqModel): BaseResponse<LoginRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponse(
                resources = ApiAuthResources.LOGIN,
                body = model
            )
        }
    }

}