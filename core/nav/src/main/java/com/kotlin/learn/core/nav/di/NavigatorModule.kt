package com.kotlin.learn.core.nav.di

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.kotlin.learn.core.nav.NavControllerBinder
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.nav.navigator.MovieNavigator
import com.kotlin.learn.core.nav.navigator.ParentNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideParentNavigator(): ParentNavigator = ParentNavigator()

    @Singleton
    @Provides
    fun provideBinder(): NavControllerBinder = NavControllerBinder()

}
