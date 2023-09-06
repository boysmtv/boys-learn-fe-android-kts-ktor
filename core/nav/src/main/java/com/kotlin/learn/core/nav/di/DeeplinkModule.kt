package com.kotlin.learn.core.nav.di

import com.kotlin.learn.core.nav.deeplink.DeeplinkNavigation
import com.kotlin.learn.core.nav.navigator.DeeplinkNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DeeplinkModule {

    @Binds
    abstract fun bindDeeplinkNavigation(navigation: DeeplinkNavigator): DeeplinkNavigation

}