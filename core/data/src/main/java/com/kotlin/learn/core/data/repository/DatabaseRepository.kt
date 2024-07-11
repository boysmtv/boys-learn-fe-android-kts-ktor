package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.db.FavouriteEntity
import com.kotlin.learn.core.model.db.HeartbeatEntity
import com.kotlin.learn.core.model.db.UserEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun getUser(): Flow<Result<List<UserEntity>>>

    fun getHeartbeat(): Flow<Result<List<HeartbeatEntity>>>

    fun getFavourite(): Flow<Result<List<FavouriteEntity>>>

    fun addUser(data: UserEntity): Flow<Result<String>>

    fun addHeartbeat(data: HeartbeatEntity): Flow<Result<String>>

    fun addFavourite(data: FavouriteEntity): Flow<Result<String>>

}