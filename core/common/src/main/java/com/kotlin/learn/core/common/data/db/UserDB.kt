package com.kotlin.learn.core.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.learn.core.common.data.db.dao.UserDao
import com.kotlin.learn.core.model.db.FavouriteEntity
import com.kotlin.learn.core.model.db.HeartbeatEntity
import com.kotlin.learn.core.model.db.UserEntity

@Database(
    entities = [UserEntity::class, HeartbeatEntity::class, FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDB : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}