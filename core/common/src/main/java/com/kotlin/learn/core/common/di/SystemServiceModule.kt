package com.kotlin.learn.core.common.di

import dagger.Module
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