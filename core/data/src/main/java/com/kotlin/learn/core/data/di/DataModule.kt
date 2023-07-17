package com.kotlin.learn.core.data.di

import com.kotlin.learn.core.data.repository.AuthRepository
import com.kotlin.learn.core.data.repository.AuthRepositoryImpl
import com.kotlin.learn.core.data.repository.MovieRepository
import com.kotlin.learn.core.data.repository.MovieRepositoryImpl
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
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

}