package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginModel
import com.kotlin.learn.core.model.RegisterModel
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserUseCase {

    // TODO : start region to spring backend
    // ===============================================================

    fun getUser(model: UserModel): Flow<Result<BaseResponse<UserModel>>>

    fun postUser(model: UserModel): Flow<Result<BaseResponse<RegisterModel>>>

    fun putUser(model: UserModel): Flow<Result<BaseResponse<RegisterModel>>>

    fun postAuth(model: UserModel): Flow<Result<BaseResponse<LoginModel>>>

    // TODO : start region to firebase
    // ===============================================================

    fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>>

    fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>


    // TODO : start region to firestore
    // ===============================================================

    fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun <T> updateUserToFirestore(
        id: String,
        filter: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun <T : Any> fetchUserFromFirestoreAsync(
        filter: Pair<String, String>,
        resources: T,
    ): Flow<ResultCallback<T>>

    fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>,
    ): Flow<ResultCallback<String>>

}
