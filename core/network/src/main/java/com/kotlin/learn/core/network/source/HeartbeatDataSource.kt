package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.HeartbeatModel

interface HeartbeatDataSource {

    // TODO : start region to firestore
    // ===============================================================

    fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

}