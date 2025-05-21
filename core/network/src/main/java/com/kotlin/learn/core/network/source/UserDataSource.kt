package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginModel
import com.kotlin.learn.core.model.RegisterModel
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    // TODO : start region to spring backend
    // ===============================================================

    suspend fun getUser(model: UserModel): BaseResponse<UserModel>

    suspend fun postUser(model: UserModel): BaseResponse<RegisterModel>

    suspend fun putUser(model: UserModel): BaseResponse<RegisterModel>

    suspend fun postAuth(model: UserModel): BaseResponse<LoginModel>


    // TODO : start region to firebase
    // ===============================================================

    suspend fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    )

    suspend fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    )


    // TODO : start region to firestore
    // ===============================================================

    fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun <T> updateUserToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    )

    fun <T : Any> fetchUserFromFirestore(
        filter: Pair<String, String>,
        resources: T
    ) : Flow<ResultCallback<T>>

    fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>,
    ) : Flow<ResultCallback<String>>

}