package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.data.db.dao.UserDao
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.db.FavouriteEntity
import com.kotlin.learn.core.model.db.HeartbeatEntity
import com.kotlin.learn.core.model.db.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : DatabaseRepository {

    override fun getUser(): Flow<Result<List<UserEntity>>> =
        flow {
            emit(Result.Loading)
            val value = userDao.getUser()
            if (value.isNotEmpty()) {
                emit(Result.Success(value))
            } else {
                emit(Result.Error(Exception("Data is empty")))
            }
        }.flowOn(Dispatchers.IO)

    override fun getHeartbeat(): Flow<Result<List<HeartbeatEntity>>> =
        flow {
            emit(Result.Loading)
            val value = userDao.getHeartbeat()
            if (value.isNotEmpty()) {
                emit(Result.Success(value))
            } else {
                emit(Result.Error(Exception("Data is empty")))
            }
        }.flowOn(Dispatchers.IO)

    override fun getFavourite(): Flow<Result<List<FavouriteEntity>>> =
        flow {
            emit(Result.Loading)
            val value = userDao.getFavorite()
            if (value.isNotEmpty()) {
                emit(Result.Success(value))
            } else {
                emit(Result.Error(Exception("Data is empty")))
            }
        }.flowOn(Dispatchers.IO)

    override fun addUser(data: List<UserEntity>): Flow<Result<String>> =
        flow {
            emit(Result.Loading)
            val value = userDao.addUser(data)
            if (value > 0) {
                emit(Result.Success("Success"))
            } else {
                emit(Result.Error(Exception("Data is empty")))
            }
        }.flowOn(Dispatchers.IO)


    override fun addHeartbeat(data: List<HeartbeatEntity>): Flow<Result<String>> =
        flow {
            emit(Result.Loading)
            val value = userDao.addHeartbeat(data)
            if (value > 0) {
                emit(Result.Success("Success"))
            } else {
                emit(Result.Error(Exception("Data is empty")))
            }
        }.flowOn(Dispatchers.IO)

    override fun addFavourite(data: List<FavouriteEntity>): Flow<Result<String>> =
        flow {
            emit(Result.Loading)
            val value = userDao.addFavorite(data)
            if (value > 0) {
                emit(Result.Success("Success"))
            } else {
                emit(Result.Error(Exception("Data is empty")))
            }
        }.flowOn(Dispatchers.IO)

}