package com.kotlin.learn.feature.services.common

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ThreadProfile : AppCompatActivity() {

    private val tag = this::class.java.simpleName

    private lateinit var context: Context


    private lateinit var dataStorePreferences: DataStorePreferences

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    fun initComponent(context: Context, dataStorePreferences: DataStorePreferences) {
        this.context = context
        this.dataStorePreferences = dataStorePreferences
    }

    private fun getToken(): String {
        var token = Constant.EMPTY_STRING
        coroutineScope.launch {
            token = withContext(Dispatchers.IO) {
                dataStorePreferences.getString(
                    PreferenceConstants.Authorization.PREF_FCM_TOKEN
                ).getOrNull().orEmpty()
            }
        }
        return token
    }

    fun postToken() {
        val token = getToken()
        if (token.isEmpty()) {
            Log.e(tag, "Token-fetch: $token")
        }
    }

}