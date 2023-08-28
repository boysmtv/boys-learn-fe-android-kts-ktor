package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.HeartbeatModel
import kotlinx.coroutines.flow.Flow

interface HeartbeatUseCase {

    // TODO : start region to firestore
    // ===============================================================

    fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) : Flow<Result<Any?>>

}
