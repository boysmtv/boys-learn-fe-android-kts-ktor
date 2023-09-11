package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.UserModel

interface ProfileDataSource {

    // TODO : start region to firestore
    // ===============================================================

    fun storeProfileToFirestore(
        id: String,
        model: UserModel,
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
        resources: T,
        onLoad: () -> Unit,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    )

}