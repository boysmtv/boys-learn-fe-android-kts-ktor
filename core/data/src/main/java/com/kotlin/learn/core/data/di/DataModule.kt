package com.kotlin.learn.core.data.di

import com.kotlin.learn.core.data.repository.DatabaseRepository
import com.kotlin.learn.core.data.repository.DatabaseRepositoryImpl
import com.kotlin.learn.core.data.repository.HeartbeatRepository
import com.kotlin.learn.core.data.repository.HeartbeatRepositoryImpl
import com.kotlin.learn.core.data.repository.MovieRepository
import com.kotlin.learn.core.data.repository.MovieRepositoryImpl
import com.kotlin.learn.core.data.repository.UserRepository
import com.kotlin.learn.core.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository

    @Binds
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    fun bindHeartbeatRepository(
        heartbeatRepositoryImpl: HeartbeatRepositoryImpl,
    ): HeartbeatRepository

    @Binds
    fun bindDatabaseRepository(
        databaseRepositoryImpl: DatabaseRepositoryImpl,
    ): DatabaseRepository

}