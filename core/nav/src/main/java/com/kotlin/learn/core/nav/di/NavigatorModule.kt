package com.kotlin.learn.core.nav.di

import com.kotlin.learn.core.nav.NavControllerBinder
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.nav.navigator.MovieNavigator
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
    fun provideMovieNavigator(): MovieNavigator = MovieNavigator()

    @Singleton
    @Provides
    fun provideAuthNavigator(): AuthNavigator = AuthNavigator()

    @Singleton
    @Provides
    fun provideBinder(): NavControllerBinder = NavControllerBinder()

}
