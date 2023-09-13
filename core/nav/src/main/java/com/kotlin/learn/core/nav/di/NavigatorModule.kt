package com.kotlin.learn.core.nav.di

import com.kotlin.learn.core.nav.navigator.ParentNavigator
import com.kotlin.learn.core.nav.navigator.MovieNavigator
import com.kotlin.learn.core.nav.navigator.MenuNavigator
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
    fun provideAuthNavigator(): ParentNavigator = ParentNavigator()

    @Singleton
    @Provides
    fun provideParentNavigator(): MenuNavigator = MenuNavigator()

}
