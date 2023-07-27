package com.kotlin.learn.core.common.di

import android.content.SharedPreferences
import com.kotlin.learn.core.common.util.security.SharedPrefManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object SystemServiceModule {

//    @Provides
//    fun provideSharedPreferences(
//        sharedPrefManager: SharedPrefManager
//    ): SharedPreferences = sharedPrefManager.getSharedPref()

}