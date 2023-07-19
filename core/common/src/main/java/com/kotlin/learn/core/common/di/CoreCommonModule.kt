package com.kotlin.learn.core.common.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
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

//    @Singleton
//    @Provides
//    fun provideCorePrefManager(@ApplicationContext app: Context) = CorePrefManager(app as Application)
//
//    @Singleton
//    @Provides
//    fun provideSecurePrefManager(corePrefManager: CorePrefManager) = SecurePrefManager(corePrefManager)
//
//    @Singleton
//    @Provides
//    fun provideCorePlainPrefManager(@ApplicationContext app: Context) = CorePlainPrefManager(app)

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(secretPreferences, Context.MODE_PRIVATE)
    }

    private val secretPreferences = "encrypted-based-preference"
}