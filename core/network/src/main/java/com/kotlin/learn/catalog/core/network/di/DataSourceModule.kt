package com.kotlin.learn.catalog.core.network.di

import com.kotlin.learn.catalog.core.network.source.AuthDataSource
import com.kotlin.learn.catalog.core.network.source.AuthDataSourceImpl
import com.kotlin.learn.catalog.core.network.source.MovieDataSource
import com.kotlin.learn.catalog.core.network.source.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun MovieDataSourceImpl.binds(): MovieDataSource

    @Binds
    fun AuthDataSourceImpl.binds(): AuthDataSource

}