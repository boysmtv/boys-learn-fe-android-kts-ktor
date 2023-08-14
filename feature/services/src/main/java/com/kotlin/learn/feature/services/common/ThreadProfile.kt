package com.kotlin.learn.feature.services.common

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.domain.UserUseCase
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.core.utilities.extension.isAvailable
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
    }

    private suspend fun getToken(): String = withContext(Dispatchers.IO) {
        dataStore.getString(
            PreferenceConstants.Authorization.PREF_FCM_TOKEN
        ).getOrNull().orEmpty()
    }

    private suspend fun getUser(): String = withContext(Dispatchers.IO) {
        dataStore.getString(
            PreferenceConstants.Authorization.PREF_USER
        ).getOrNull().orEmpty()
    }

    private suspend fun storeToPreferences(token: String) {
        withContext(Dispatchers.IO) {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                token
            )
        }
    }

    private fun postToken() {
        val token = runBlocking { getToken() }
        val user = runBlocking { getUser() }
        if (user.isNotBlank()) {
            try {
                val userModel = jsonUtil.fromJson<UserModel>(user)
                if (token.isNotBlank()) {
                    if (userModel != null) {
                        Log.e(tag, "postToken: user has sent")
                        coroutineScope.launch {
                            withContext(Dispatchers.IO) {
                                useCase.putUser(
                                    userModel.apply {
                                        id = userModel.id
                                        email = userModel.email
                                        idToken = token
                                    }
                                ).collect()
                            }
                        }
                    } else Log.e(tag, "postToken: user model is empty")
                } else Log.e(tag, "postToken: token is empty")
            } catch (ex: Exception) {
                Log.e(tag, "postToken: token is error, ${ex.message}")
            }
        } else Log.e(tag, "postToken: user is empty, $user")
    }

    fun getTokenFirebase() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener { token: String ->
            if (!TextUtils.isEmpty(token)) {
                Log.e(tag, "Token-getTokenFirebase: $token")
                runBlocking {
                    if (getToken().isEmpty()) {
                        storeToPreferences(token)
                        postToken()
                    }
                }
            }
        }.addOnFailureListener { _: Exception? -> }.addOnCanceledListener {}
            .addOnCompleteListener { task: Task<String> ->
                Log.e(tag, "Token-getTokenFirebase: ${task.result}")
                runBlocking {
                    if (getToken().isEmpty()) {
                        storeToPreferences(task.result)
                        postToken()
                    }
                }
            }
    }

}