package com.kotlin.learn.core.domain

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.db.FavouriteEntity
import com.kotlin.learn.core.model.db.HeartbeatEntity
import com.kotlin.learn.core.model.db.UserEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseUseCase {

    fun getUser(): Flow<Result<List<UserEntity>>>

    fun getHeartbeat(): Flow<Result<List<HeartbeatEntity>>>

    fun getFavourite(): Flow<Result<List<FavouriteEntity>>>

}