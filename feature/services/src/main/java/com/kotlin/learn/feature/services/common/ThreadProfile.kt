package com.kotlin.learn.feature.services.common

import android.content.Context
import android.util.Log
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.core.utilities.extension.isAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ThreadProfile {

    private val tag = this::class.java.simpleName

    private lateinit var context: Context

    private lateinit var dataStorePreferences: DataStorePreferences

    fun initComponent(context: Context, dataStorePreferences: DataStorePreferences) {
        this.context = context
        this.dataStorePreferences = dataStorePreferences
    }

    private suspend fun getToken(): String = withContext(Dispatchers.IO) {
        dataStorePreferences.getString(
            PreferenceConstants.Authorization.PREF_FCM_TOKEN
        ).getOrNull().orEmpty()
    }

    fun postToken() {
        val token = runBlocking {
            getToken()
        }
        Log.e(tag, "Token-fetch: ${token.isAvailable()}")
    }

}