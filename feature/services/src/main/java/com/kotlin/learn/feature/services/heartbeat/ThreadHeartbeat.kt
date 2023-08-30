package com.kotlin.learn.feature.services.heartbeat

import android.content.Context
import android.util.Log
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.ServiceUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.domain.HeartbeatUseCase
import com.kotlin.learn.core.model.HeartbeatModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.core.utilities.TransactionUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ThreadHeartbeat {

    private val tag = this::class.java.simpleName

    private lateinit var context: Context

    private lateinit var jsonUtil: JsonUtil

    private lateinit var dataStore: DataStorePreferences

    private lateinit var useCase: HeartbeatUseCase

    private lateinit var serviceUtil: ServiceUtil

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    fun initComponent(
        context: Context,
        jsonUtil: JsonUtil,
        preferences: DataStorePreferences,
        useCase: HeartbeatUseCase
    ) {
        this.context = context
        this.jsonUtil = jsonUtil
        this.dataStore = preferences
        this.useCase = useCase

        serviceUtil = ServiceUtil(context)
    }

    fun storeHeartbeat(heartbeatModel: HeartbeatModel) {
        val userData = runBlocking { getUser() }
        if (userData.isNotEmpty()) {
            val userModel = jsonUtil.fromJson<UserModel>(userData)
            userModel?.let {
                heartbeatModel.apply {
                    user = it
                }
            }
        }

        try {
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    useCase.storeHeartbeatToFirestore(
                        id = TransactionUtil.generateTransactionID(),
                        model = heartbeatModel,
                        onLoad = { },
                        onSuccess = { },
                        onError = { }
                    ).collect()
                }
            }
        } catch (ex: Exception) {
            Log.e(tag, "storeHeartbeat: heartbeat is error, ${ex.message}")
        }
    }

    suspend fun getLocation(): String = withContext(Dispatchers.IO) {
        dataStore.getString(
            PreferenceConstants.Authorization.PREF_LOCATION
        ).getOrNull().orEmpty()
    }

    private suspend fun getUser(): String = withContext(Dispatchers.IO) {
        dataStore.getString(
            PreferenceConstants.Authorization.PREF_USER
        ).getOrNull().orEmpty()
    }

    private suspend fun storeToPreferences(message: String) {
        withContext(Dispatchers.IO) {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_HEARTBEAT,
                message
            )
        }
    }

}