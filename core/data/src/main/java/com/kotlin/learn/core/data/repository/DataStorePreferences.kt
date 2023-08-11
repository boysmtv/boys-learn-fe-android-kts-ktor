package com.kotlin.learn.core.data.repository

interface DataStorePreferences {

    suspend fun setString(key: String, message: String)

    suspend fun getString(key: String): Result<String>

    suspend fun setInt(key: String, message: Int)

    suspend fun getInt(key: String): Result<Int>

    suspend fun setLong(key: String, message: Long)

    suspend fun getLong(key: String): Result<Long>

    suspend fun setBoolean(key: String, message: Boolean)

    suspend fun getBoolean(key: String): Result<Boolean>

    suspend fun removePreferences(key: String)

    suspend fun clearPreferences()

}