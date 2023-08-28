package com.kotlin.learn.core.network.di

import com.kotlin.learn.core.network.source.HeartbeatDataSource
import com.kotlin.learn.core.network.source.HeartbeatDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HeartbeatSourceModule {

    @Binds
    fun HeartbeatDataSourceImpl.binds(): HeartbeatDataSource

}