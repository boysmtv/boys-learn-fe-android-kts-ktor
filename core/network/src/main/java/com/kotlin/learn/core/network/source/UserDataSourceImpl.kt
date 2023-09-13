package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.ApiUserResources
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.network.ApiFirebaseResources
import com.kotlin.learn.core.network.firebase.FirebaseClient
import com.kotlin.learn.core.network.firestore.FirestoreClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
    private val firebaseClient: FirebaseClient,
    private val firestoreClient: FirestoreClient,
) : UserDataSource {

    // TODO : start region to spring backend
    // ===============================================================

    override suspend fun getUser(model: UserModel): BaseResponse<UserModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponseFromSpring(
                resources = ApiUserResources.PROFILE,
                body = model
            )
        }
    }

    override suspend fun postUser(model: UserModel): BaseResponse<RegisterRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponseFromSpring(
                resources = ApiUserResources.REGISTER,
                body = model
            )
        }
    }

    override suspend fun putUser(model: UserModel): BaseResponse<RegisterRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.putAPIwithResponseFromSpring(
                resources = ApiUserResources.PROFILE,
                body = model
            )
        }
    }

    override suspend fun postAuth(model: UserModel): BaseResponse<LoginRespModel> {
        return withContext(Dispatchers.IO) {
            ktorClient.postAPIwithResponseFromSpring(
                resources = ApiUserResources.LOGIN,
                body = model
            )
        }
    }


    // TODO : start region to firebase
    // ===============================================================

    override suspend fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        firebaseClient.storeRequestToFirebase(
            data = model,
            firestoreTable = ApiFirebaseResources.USER,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override suspend fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ) = firebaseClient.fetchRequestFromFirebase(
        id = id,
        firestoreTable = ApiFirebaseResources.USER,
        resources = resources,
        onSuccess = onSuccess,
        onError = onError
    )


    // TODO : start region to firestore
    // ===============================================================

    override fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.storeRequestToFirestore(
            id = id,
            data = model,
            collection = ApiFirebaseResources.USER,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun <T> updateUserToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.updateRequestToFirestore(
            id = id,
            data = model,
            collection = ApiFirebaseResources.USER,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.fetchUserFromFirestore(
            filter = filter,
            collection = ApiFirebaseResources.USER,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun <T : Any> fetchUserFromFirestore(
        filter: Pair<String, String>,
        resources: T
    ): Flow<ResultCallback<T>> {
        return firestoreClient.fetchDataFromFirestoreAsync(
            filter = filter,
            resources = resources,
            collection = ApiFirebaseResources.USER
        )
    }

    override fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>
    ): Flow<ResultCallback<String>> {
        return firestoreClient.updateRequestToFirestoreAsync(
            id = id,
            data = model,
            collection = ApiFirebaseResources.USER
        )
    }

}