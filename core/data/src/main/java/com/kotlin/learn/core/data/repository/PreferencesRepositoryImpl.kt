package com.kotlin.learn.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
) : PreferencesRepository {

    private companion object {
        val KEY_AUTHORIZATION = stringPreferencesKey(name = "auth")
    }

    override suspend fun setAuthorization(auth: String) {
        Result.runCatching {
            userDataStorePreferences.edit { preferences ->
                preferences[KEY_AUTHORIZATION] = auth
            }
        }
    }

    override suspend fun getAuthorization(): Result<String> {
        return Result.runCatching {
            val flow = userDataStorePreferences.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[KEY_AUTHORIZATION]
                }
            val value = flow.firstOrNull() ?: "" // we only care about the 1st value
            value
        }
    }

}