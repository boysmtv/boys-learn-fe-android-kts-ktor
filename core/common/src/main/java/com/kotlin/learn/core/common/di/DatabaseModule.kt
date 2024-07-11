package com.kotlin.learn.core.common.di

import android.content.Context
import androidx.room.Room
import com.kotlin.learn.core.common.data.db.UserDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideUserDB(@ApplicationContext context : Context) : UserDB {
        return Room.databaseBuilder(context, UserDB::class.java, "UserDB").build()
    }

    @Singleton
    @Provides
    fun provideYourDao(db: UserDB) = db.getUserDao()

}