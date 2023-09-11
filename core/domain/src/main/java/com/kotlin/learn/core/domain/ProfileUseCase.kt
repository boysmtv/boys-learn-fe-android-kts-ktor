package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {

    // TODO : start region to firestore
    // ===============================================================

    fun storeProfileToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun <T> updateProfileToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun <T : Any> fetchProfileFromFirestore(
        filter: Pair<String, String>,
        resources: T,
        onLoad: () -> Unit,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

}
