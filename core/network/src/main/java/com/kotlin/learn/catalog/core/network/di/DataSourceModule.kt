package com.kotlin.learn.catalog.core.network.di

import com.kotlin.learn.catalog.core.network.source.NetworkDataSource
import com.kotlin.learn.catalog.core.network.source.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun NetworkDataSourceImpl.binds(): NetworkDataSource
}