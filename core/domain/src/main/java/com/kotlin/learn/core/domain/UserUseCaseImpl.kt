package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.data.repository.UserRepository
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginModel
import com.kotlin.learn.core.model.RegisterModel
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : UserUseCase {

    // TODO : start region to spring backend
    // ===============================================================

    override fun getUser(model: UserModel): Flow<Result<BaseResponse<UserModel>>> =
        repository.getUser(model = model)

    override fun postUser(model: UserModel): Flow<Result<BaseResponse<RegisterModel>>> =
        repository.postUser(model = model)

    override fun putUser(model: UserModel): Flow<Result<BaseResponse<RegisterModel>>> =
        repository.putUser(model = model)

    override fun postAuth(model: UserModel): Flow<Result<BaseResponse<LoginModel>>> =
        repository.postAuth(model = model)

    // TODO : start region to firebase
    // ===============================================================

    override fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>> = repository.storeUserToFirebase(model, onSuccess, onError)

    override fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> = repository.fetchUserFromFirebase(id, resources, onSuccess, onError)


    // TODO : start region to firestore
    // ===============================================================

    override fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> = repository.storeUserToFirestore(id, model, onLoad, onSuccess, onError)

    override fun <T> updateUserToFirestore(
        id: String,
        filter: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> = repository.updateUserToFirestore(id, filter, onLoad, onSuccess, onError)

    override fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> = repository.fetchUserFromFirestore(filter, onLoad, onSuccess, onError)

    override fun <T : Any> fetchUserFromFirestoreAsync(
        filter: Pair<String, String>,
        resources: T,
    ): Flow<ResultCallback<T>> = repository.fetchUserFromFirestore(filter, resources)

    override fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>
    ): Flow<ResultCallback<String>> = repository.updateUserToFirestoreAsync(id, model)

}