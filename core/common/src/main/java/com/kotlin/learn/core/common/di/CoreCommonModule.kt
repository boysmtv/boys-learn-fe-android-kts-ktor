package com.kotlin.learn.core.common.di

import android.app.Application
import android.content.Context
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.security.CorePlainPrefManager
import com.kotlin.learn.core.common.util.security.CorePrefManager
import com.kotlin.learn.core.common.util.security.SecurePrefManager
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreCommonModule {

    @Singleton
    @Provides
    fun provideCorePrefManager(@ApplicationContext app: Context) = CorePrefManager(app as Application)

    @Singleton
    @Provides
    fun provideSecurePrefManager(corePrefManager: CorePrefManager) = SecurePrefManager(corePrefManager)

    @Singleton
    @Provides
    fun provideCorePlainPrefManager(@ApplicationContext app: Context) = CorePlainPrefManager(app as Application)

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

}