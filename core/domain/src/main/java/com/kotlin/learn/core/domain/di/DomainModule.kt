package com.kotlin.learn.core.domain.di

import com.kotlin.learn.core.domain.DatabaseUseCase
import com.kotlin.learn.core.domain.DatabaseUseCaseImpl
import com.kotlin.learn.core.domain.HeartbeatUseCase
import com.kotlin.learn.core.domain.HeartbeatUseCaseImpl
import com.kotlin.learn.core.domain.MovieUseCase
import com.kotlin.learn.core.domain.MovieUseCaseImpl
import com.kotlin.learn.core.domain.UserUseCase
import com.kotlin.learn.core.domain.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindMovieUseCase(
        movieUseCaseImpl: MovieUseCaseImpl,
    ): MovieUseCase

    @Binds
    fun bindUserUseCase(
        userUseCaseImpl: UserUseCaseImpl,
    ): UserUseCase

    @Binds
    fun bindHeartbeatUseCase(
        heartbeatUseCaseImpl: HeartbeatUseCaseImpl
    ): HeartbeatUseCase

    @Binds
    fun bindDatabaseUseCase(
        databaseUseCaseImpl: DatabaseUseCaseImpl
    ): DatabaseUseCase

}