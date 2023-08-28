package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.HeartbeatModel
import com.kotlin.learn.core.network.ApiFirebaseResources
import com.kotlin.learn.core.network.firestore.FirestoreClient
import javax.inject.Inject

class HeartbeatDataSourceImpl @Inject constructor(
    private val firestoreClient: FirestoreClient,
) : HeartbeatDataSource {

    // TODO : start region to firestore
    // ===============================================================

    override fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.storeRequestToFirestore(
            id = id,
            data = model,
            collection = ApiFirebaseResources.HEARTBEAT,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

}