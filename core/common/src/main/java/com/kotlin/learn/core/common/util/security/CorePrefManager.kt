package com.kotlin.learn.core.common.util.security

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class CorePrefManager(application: Application) {

    private val secretPreferences = "encrypted-based-preference"
    private val masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        secretPreferences,
        masterKeys,
        application,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun setStringSet(key: String, value: Set<String>) {
        sharedPreferences.edit().putStringSet(key, value).apply()
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun setLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun setFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    @Synchronized
    fun getString(key: String): String? = sharedPreferences.getString(key, null)

    fun getStringSet(key: String) = sharedPreferences.getStringSet(key, emptySet()).orEmpty()

    fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun getLong(key: String): Long = sharedPreferences.getLong(key, 0L)

    fun getFloat(key: String): Float = sharedPreferences.getFloat(key, 0F)

    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    fun isContain(key: String): Boolean = sharedPreferences.contains(key)

    @Synchronized
    fun remove(key: String) = sharedPreferences.edit().remove(key).commit()

    @Synchronized
    fun clear() = sharedPreferences.edit().clear().commit()
}