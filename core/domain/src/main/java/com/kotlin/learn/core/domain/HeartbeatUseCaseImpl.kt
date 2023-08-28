package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.data.repository.HeartbeatRepository
import com.kotlin.learn.core.model.HeartbeatModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeartbeatUseCaseImpl @Inject constructor(
    private val repository: HeartbeatRepository
) : HeartbeatUseCase {

    // TODO : start region to firestore
    // ===============================================================

    override fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.storeHeartbeatToFirestore(id, model, onLoad, onSuccess, onError)
    }

}