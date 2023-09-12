package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.common.util.network.ResultCallback
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {

    // TODO : start region to firestore
    // ===============================================================

    fun <T: Any> storeProfileToFirestore(
        id: String,
        model: T,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun <T> updateProfileToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

     fun <T : Any> fetchProfileFromFirestore(
        filter: Pair<String, String>,
        resources: T
    ) : Flow<ResultCallback<T>>

}