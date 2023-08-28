package com.kotlin.learn.feature.services.location

import android.content.Context
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.ServiceUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.model.LocationModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThreadLocation {

    private lateinit var context: Context

    private lateinit var jsonUtil: JsonUtil

    private lateinit var dataStore: DataStorePreferences

    private lateinit var serviceUtil: ServiceUtil

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    fun initComponent(
        context: Context,
        jsonUtil: JsonUtil,
        preferences: DataStorePreferences,
    ) {
        this.context = context
        this.jsonUtil = jsonUtil
        this.dataStore = preferences

        serviceUtil = ServiceUtil(context)
    }

    fun storeLocation(locationModel: LocationModel) {
        coroutineScope.launch {
            storeToPreferences(
                jsonUtil.toJson(locationModel)
            )
        }
    }

    suspend fun getLocation(): String = withContext(Dispatchers.IO) {
        dataStore.getString(
            PreferenceConstants.Authorization.PREF_LOCATION
        ).getOrNull().orEmpty()
    }

    private suspend fun storeToPreferences(message: String) {
        withContext(Dispatchers.IO) {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_LOCATION,
                message
            )
        }
    }
}