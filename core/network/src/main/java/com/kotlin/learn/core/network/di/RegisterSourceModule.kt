package com.kotlin.learn.core.network.di

import com.kotlin.learn.core.network.source.RegisterDataSource
import com.kotlin.learn.core.network.source.RegisterDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
interface RegisterSourceModule {

    @Binds
    fun RegisterDataSourceImpl.binds(): RegisterDataSource

}