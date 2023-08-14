package com.kotlin.learn.core.network.di

import com.kotlin.learn.core.network.source.UserDataSource
import com.kotlin.learn.core.network.source.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserSourceModule {

    @Binds
    fun UserDataSourceImpl.binds(): UserDataSource

}