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

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.kotlin.learn.feature.auth.presentation.ui.user_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        preferencesRepositoryImpl: DataStorePreferencesImpl
    ): DataStorePreferences

    companion object {
        @Provides
        @Singleton
        fun providePreferencesModule(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.userDataStore
        }
    }
}