package com.kotlin.learn.feature.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.event.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.common.data.preferences.DataStorePreferences
import com.kotlin.learn.core.domain.UserUseCase
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.LoginRespModel
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: UserUseCase,
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil,
) : ViewModel() {

    // TODO : start region to spring backend
    // ===============================================================

    private val _getUser: MutableStateFlow<Result<BaseResponse<UserModel>>> = MutableStateFlow(Result.Waiting)
    var getUser = _getUser.asStateFlow()

    private val _postUser: MutableStateFlow<Result<BaseResponse<RegisterRespModel>>> = MutableStateFlow(Result.Waiting)
    val postUser = _postUser.asStateFlow()

    private val _putUser: MutableStateFlow<Result<BaseResponse<RegisterRespModel>>> = MutableStateFlow(Result.Waiting)
    var putUser = _putUser.asStateFlow()

    private val _postAuth: MutableStateFlow<Result<BaseResponse<LoginRespModel>>> = MutableStateFlow(Result.Waiting)
    val postAuth = _postAuth.asStateFlow()

    fun getUser(userModel: UserModel) {
        useCase.getUser(userModel)
            .onEach { _getUser.value = it }
            .launchIn(viewModelScope)
    }

    fun postUser(model: UserModel) {
        useCase.postUser(model)
            .onEach { _postUser.value = it }
            .launchIn(viewModelScope)
    }

    fun putUser(userModel: UserModel) {
        useCase.putUser(userModel)
            .onEach { _putUser.value = it }
            .launchIn(viewModelScope)
    }

    fun postAuth(model: UserModel) {
        useCase.postAuth(model = model)
            .onEach { _postAuth.value = it }
            .launchIn(viewModelScope)
    }

    // TODO : start region to datastore
    // ===============================================================

    fun storeUserToDatastore(user: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_USER,
                user
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

    fun fetchUserFromDatastore() =
        flow {
            val data = dataStore.getString(PreferenceConstants.Authorization.PREF_USER).getOrNull().orEmpty()
            if (data.isNotEmpty() && data.isNotBlank()) {
                try {
                    val model = jsonUtil.fromJson<UserModel>(data)
                    model?.let {
                        emit(DataStoreCacheEvent.FetchSuccess(model))
                    }
                    if (model == null) emit(DataStoreCacheEvent.FetchError)
                } catch (e: Exception) {
                    emit(DataStoreCacheEvent.FetchError)
                }
            } else
                emit(DataStoreCacheEvent.FetchError)
        }

    fun fetchTokenFromDatastore() =
        flow {
            val data = dataStore.getString(PreferenceConstants.Authorization.PREF_FCM_TOKEN).getOrNull().orEmpty()
            if (data.isNotEmpty() && data.isNotBlank())
                emit(DataStoreCacheEvent.FetchSuccess(data))
            else
                emit(DataStoreCacheEvent.FetchError)
        }


    // TODO : start region to firebase
    // ===============================================================

    fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        useCase.storeUserToFirebase(model, onSuccess, onError).launchIn(viewModelScope)
    }

    fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) = useCase.fetchUserFromFirebase(
        id,
        resources,
        onSuccess,
        onError
    ).launchIn(viewModelScope)


    // TODO : start region to firestore
    // ===============================================================

    fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        useCase.storeUserToFirestore(
            id = id,
            model = model,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        ).launchIn(viewModelScope)
    }

    fun <T> updateUserToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        useCase.updateUserToFirestore(
            id = id,
            filter = model,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        ).launchIn(viewModelScope)
    }

    private val _fetchUserFirestore: MutableStateFlow<ResultCallback<UserModel>> =
        MutableStateFlow(ResultCallback.Loading)
    val fetchUserFirestore = _fetchUserFirestore.asStateFlow()

    fun fetchUserFromFirestoreAsync(
        filter: Pair<String, String>,
    ) {
        useCase.fetchUserFromFirestoreAsync(
            filter = filter,
            resources = UserModel(),
        ).onEach {
            _fetchUserFirestore.value = it
        }.launchIn(viewModelScope)
    }

    private val _updateUserFirestore: MutableStateFlow<ResultCallback<String>> =
        MutableStateFlow(ResultCallback.Loading)
    val updateUserFirestore = _updateUserFirestore.asStateFlow()

    fun <T : Any> updateUserFromFirestoreAsync(
        id: String,
        model: Map<String, T>,
    ) {
        useCase.updateUserToFirestoreAsync(
            id = id,
            model = model
        ).onEach {
            _updateUserFirestore.value = it
        }.launchIn(viewModelScope)
    }

}