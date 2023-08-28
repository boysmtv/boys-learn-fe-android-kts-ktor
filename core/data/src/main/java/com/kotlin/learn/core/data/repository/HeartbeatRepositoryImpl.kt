package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.util.network.execute
import com.kotlin.learn.core.model.HeartbeatModel
import com.kotlin.learn.core.network.source.HeartbeatDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HeartbeatRepositoryImpl @Inject constructor(
    private val network: HeartbeatDataSource
) : HeartbeatRepository {

    override fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.storeHeartbeatToFirestore(id, model, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

}