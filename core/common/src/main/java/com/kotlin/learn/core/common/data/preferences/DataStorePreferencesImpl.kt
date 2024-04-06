package com.kotlin.learn.core.common.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kotlin.learn.core.utilities.Constant
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStorePreferencesImpl @Inject constructor(
    private val preferencesDataStore: DataStore<Preferences>
) : DataStorePreferences {

    override suspend fun setString(key: String, message: String) {
        Result.runCatching {
            preferencesDataStore.edit { preferences ->
                preferences[stringPreferencesKey(name = key)] = message
            }
        }
    }

    override suspend fun getString(key: String): Result<String> = Result.runCatching {
        val flow = preferencesDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[stringPreferencesKey(name = key)]
            }
        val value = flow.firstOrNull() ?: "" // we only care about the 1st value
        value
    }

    override suspend fun setInt(key: String, message: Int) {
        Result.runCatching {
            preferencesDataStore.edit { preferences ->
                preferences[intPreferencesKey(name = key)] = message
            }
        }
    }

    override suspend fun getInt(key: String): Result<Int> = Result.runCatching {
        val flow = preferencesDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[intPreferencesKey(name = key)]
            }
        val value = flow.firstOrNull() ?: 0 // we only care about the 1st value
        value
    }

    override suspend fun setLong(key: String, message: Long) {
        Result.runCatching {
            preferencesDataStore.edit { preferences ->
                preferences[longPreferencesKey(name = key)] = message
            }
        }
    }

    override suspend fun getLong(key: String): Result<Long> = Result.runCatching {
        val flow = preferencesDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[longPreferencesKey(name = key)]
            }
        val value = flow.firstOrNull() ?: 0L
        value
    }

    override suspend fun setBoolean(key: String, message: Boolean) {
        Result.runCatching {
            preferencesDataStore.edit { preferences ->
                preferences[booleanPreferencesKey(name = key)] = message
            }
        }
    }

    override suspend fun getBoolean(key: String): Result<Boolean> = Result.runCatching {
        val flow = preferencesDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[booleanPreferencesKey(name = key)]
            }
        val value = flow.firstOrNull() ?: Constant.FALSE
        value
    }

    override suspend fun removePreferences(key: String) {
        Result.runCatching {
            preferencesDataStore.edit {
                if (it.contains(stringPreferencesKey(name = key))) {
                    it.remove(stringPreferencesKey(name = key))
                }
            }
        }
    }

    override suspend fun clearPreferences() {
        Result.runCatching {
            preferencesDataStore.edit { it.clear() }
        }
    }

}