package com.kotlin.learn.core.common.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.learn.core.model.db.FavouriteEntity
import com.kotlin.learn.core.model.db.HeartbeatEntity
import com.kotlin.learn.core.model.db.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeartbeat(heartbeat: HeartbeatEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favourite: FavouriteEntity): Long

    @Query("SELECT * FROM UserEntity")
    suspend fun getUser(): List<UserEntity>

    @Query("SELECT * FROM HeartbeatEntity")
    suspend fun getHeartbeat(): List<HeartbeatEntity>

    @Query("SELECT * FROM FavouriteEntity")
    suspend fun getFavorite(): List<FavouriteEntity>
}