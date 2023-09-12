package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.model.ProfileModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.ApiFirebaseResources
import com.kotlin.learn.core.network.firestore.FirestoreClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val firestoreClient: FirestoreClient,
) : ProfileDataSource {

    override fun <T : Any> storeProfileToFirestore(
        id: String,
        model: T,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.storeRequestToFirestore(
            id = id,
            data = model,
            collection = ApiFirebaseResources.PROFILE,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun <T> updateProfileToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.updateRequestToFirestore(
            id = id,
            data = model,
            collection = ApiFirebaseResources.PROFILE,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun <T : Any> fetchProfileFromFirestore(
        filter: Pair<String, String>,
        resources: T
    ): Flow<ResultCallback<T>> {
        return firestoreClient.fetchDataFromFirestore(
            filter = filter,
            resources = resources,
            collection = ApiFirebaseResources.PROFILE
        )
    }

}