package com.kotlin.learn.feature.services.profile

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.ServiceUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.domain.UserUseCase
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.utilities.PreferenceConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ThreadProfile {

    private val tag = this::class.java.simpleName

    private lateinit var context: Context

    private lateinit var ktorClient: KtorClient

    private lateinit var jsonUtil: JsonUtil

    private lateinit var dataStore: DataStorePreferences

    private lateinit var useCase: UserUseCase

    private lateinit var serviceUtil: ServiceUtil

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    fun initComponent(
        context: Context,
        jsonUtil: JsonUtil,
        ktorClient: KtorClient,
        preferences: DataStorePreferences,
        useCase: UserUseCase
    ) {
        this.context = context
        this.jsonUtil = jsonUtil
        this.ktorClient = ktorClient
        this.dataStore = preferences
        this.useCase = useCase

        serviceUtil = ServiceUtil(context)
    }

    suspend fun getToken(): String = withContext(Dispatchers.IO) {
        dataStore.getString(
            PreferenceConstants.Authorization.PREF_FCM_TOKEN
        ).getOrNull().orEmpty()
    }

    suspend fun getUser(): String = withContext(Dispatchers.IO) {
        dataStore.getString(
            PreferenceConstants.Authorization.PREF_USER
        ).getOrNull().orEmpty()
    }

    private suspend fun storeToPreferences(message: String) {
        withContext(Dispatchers.IO) {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                message
            )
        }
    }

    private suspend fun storeUserToPreferences(message: String) {
        withContext(Dispatchers.IO) {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_USER,
                message
            )
        }
    }

    fun getTokenFirebase() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener { message: String ->
            if (!TextUtils.isEmpty(message)) {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        storeToPreferences(message)
                        postToken()
                    }
                }
            }
        }.addOnFailureListener { _: Exception? -> }.addOnCanceledListener {}
            .addOnCompleteListener { task: Task<String> ->
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        storeToPreferences(task.result)
                        postToken()
                    }
                }
            }
    }

    private fun updateUserToken(user: String, token: String) {
        if (user.isNotBlank() && token.isNotBlank()) {
            jsonUtil.fromJson<UserModel>(user)?.let {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        storeUserToPreferences(
                            jsonUtil.toJson(
                                it.apply {
                                    idToken = token
                                }
                            )
                        )
                    }
                }
            }
        }
    }

    private fun postToken() {
        val token = runBlocking { getToken() }
        val user = runBlocking { getUser() }

        updateUserToken(user, token)

        if (user.isNotBlank() && token.isNotBlank()) {
            jsonUtil.fromJson<UserModel>(user)?.let {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        it.id?.let {
                            useCase.updateUserToFirestore(
                                id = it,
                                model = mapOf(
                                    "idToken" to token
                                ),
                                onLoad = { },
                                onSuccess = { },
                                onError = { }
                            ).collect()
                        }
                    }
                }
            }
        }
    }

}