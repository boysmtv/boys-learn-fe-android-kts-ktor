package com.kotlin.learn.core.network.di

import com.kotlin.learn.core.network.source.AuthDataSource
import com.kotlin.learn.core.network.source.AuthDataSourceImpl
import com.kotlin.learn.core.network.source.MovieDataSource
import com.kotlin.learn.core.network.source.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthSourceModule {

    @Binds
    fun AuthDataSourceImpl.binds(): AuthDataSource

}