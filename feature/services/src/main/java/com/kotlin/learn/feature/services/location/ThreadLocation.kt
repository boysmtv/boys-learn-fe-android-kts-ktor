package com.kotlin.learn.feature.services.location

import android.content.Context
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.ServiceUtil
import com.kotlin.learn.core.common.data.preferences.DataStorePreferences
import com.kotlin.learn.core.model.LocationModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ThreadLocation {

    private lateinit var context: Context

    private lateinit var jsonUtil: JsonUtil

    private lateinit var dataStore: DataStorePreferences

    fun initComponent(
        context: Context,
        jsonUtil: JsonUtil,
        preferences: DataStorePreferences,
    ) {
        this.context = context
        this.jsonUtil = jsonUtil
        this.dataStore = preferences
    }

    suspend fun getLocation(): String {
        return withContext(Dispatchers.IO) {
            dataStore.getString(
                PreferenceConstants.Authorization.PREF_LOCATION
            ).getOrNull().orEmpty()
        }
    }

    suspend fun storeLocation(locationModel: LocationModel) {
        withContext(Dispatchers.IO) {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_LOCATION,
                jsonUtil.toJson(locationModel)
            )
        }
    }

}