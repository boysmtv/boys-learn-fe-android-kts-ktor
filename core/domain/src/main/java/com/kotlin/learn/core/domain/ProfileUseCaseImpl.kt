package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.data.repository.ProfileRepository
import com.kotlin.learn.core.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
) : ProfileUseCase {

    // TODO : start region to firestore
    // ===============================================================

    override fun storeProfileToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.storeProfileToFirestore(id, model, onLoad, onSuccess, onError)
    }

    override fun <T> updateProfileToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.updateProfileToFirestore(id, model, onLoad, onSuccess, onError)
    }

    override fun <T : Any> fetchProfileFromFirestore(
        filter: Pair<String, String>,
        resources: T,
        onLoad: () -> Unit,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> {
        return repository.fetchProfileFromFirestore(filter, resources, onLoad, onSuccess, onError)
    }

}