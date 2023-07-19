package com.kotlin.learn.core.data.repository

interface PreferencesRepository {

    suspend fun setAuthorization(auth: String)

    suspend fun getAuthorization(): Result<String>

}