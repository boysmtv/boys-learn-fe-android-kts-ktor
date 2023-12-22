package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.data.repository.DatabaseRepository
import com.kotlin.learn.core.model.db.FavouriteEntity
import com.kotlin.learn.core.model.db.HeartbeatEntity
import com.kotlin.learn.core.model.db.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseUseCaseImpl @Inject constructor(
    private val repository: DatabaseRepository
) : DatabaseUseCase {

    override fun getUser(): Flow<Result<List<UserEntity>>> = repository.getUser()

    override fun getHeartbeat(): Flow<Result<List<HeartbeatEntity>>> = repository.getHeartbeat()

    override fun getFavourite(): Flow<Result<List<FavouriteEntity>>> = repository.getFavourite()

}