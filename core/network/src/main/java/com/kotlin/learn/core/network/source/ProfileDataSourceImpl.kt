package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.ProfileModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.ApiFirebaseResources
import com.kotlin.learn.core.network.firestore.FirestoreClient
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val firestoreClient: FirestoreClient,
) : ProfileDataSource {

    override fun <T: Any> storeProfileToFirestore(
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
        resources: T,
        onLoad: () -> Unit,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.fetchRequestFromFirestore(
            filter = filter,
            resources = resources,
            collection = ApiFirebaseResources.PROFILE,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }
}