package com.kotlin.learn.core.network.di

import com.kotlin.learn.core.network.source.ProfileDataSource
import com.kotlin.learn.core.network.source.ProfileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProfileSourceModule {

    @Binds
    fun ProfileDataSourceImpl.binds(): ProfileDataSource

}