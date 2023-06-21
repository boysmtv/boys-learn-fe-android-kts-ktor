package com.kotlin.learn.catalog.core.nav.di

import com.kotlin.learn.catalog.core.nav.navigator.MovieNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {

    @Singleton
    @Provides
    fun provideNavigator(): MovieNavigator = MovieNavigator()

}
