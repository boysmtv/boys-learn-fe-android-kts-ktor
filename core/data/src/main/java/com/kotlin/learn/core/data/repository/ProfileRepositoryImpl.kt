package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.util.network.execute
import com.kotlin.learn.core.model.ProfileModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.source.ProfileDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val network: ProfileDataSource
) : ProfileRepository {

    // TODO : start region to firestore
    // ===============================================================

    override fun <T: Any> storeProfileToFirestore(
        id: String,
        model: T,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.storeProfileToFirestore(id, model, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun <T> updateProfileToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.updateProfileToFirestore(id, model, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun <T : Any> fetchProfileFromFirestore(
        filter: Pair<String, String>,
        resources: T,
        onLoad: () -> Unit,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.fetchProfileFromFirestore(filter, resources, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)


}