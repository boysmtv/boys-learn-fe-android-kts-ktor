package com.kotlin.learn.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.security.DataStorePreferences
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
    private val dataStore: DataStorePreferences
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
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    dataStore.getString(
                        PreferenceConstants.Authorization.PREF_USER,
                    ).getOrNull().orEmpty()
                )
            )
        }

    fun fetchTokenFromDatastore() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    dataStore.getString(
                        PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                    ).getOrNull().orEmpty()
                )
            )
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

    fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = useCase.fetchUserFromFirestore(
        filter = filter,
        onLoad = onLoad,
        onSuccess = onSuccess,
        onError = onError
    ).launchIn(viewModelScope)

}