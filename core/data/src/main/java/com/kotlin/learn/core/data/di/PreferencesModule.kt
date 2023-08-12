package com.kotlin.learn.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.kotlin.learn.core.data.repository.DataStorePreferences
import com.kotlin.learn.core.data.repository.DataStorePreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.kotlin.learn.feature.auth.presentation.ui.preferences"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun bindPreferencesDataStoreRepository(
        preferencesRepositoryImpl: DataStorePreferencesImpl
    ): DataStorePreferences

    companion object {
        @Provides
        @Singleton
        fun providePreferencesDataStoreModule(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.preferencesDataStore
        }
    }
}