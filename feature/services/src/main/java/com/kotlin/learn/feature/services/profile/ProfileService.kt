package com.kotlin.learn.feature.services.profile

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.domain.UserUseCase
import com.kotlin.learn.core.network.KtorClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.annotation.Nullable
import javax.inject.Inject


@AndroidEntryPoint
class ProfileService : Service() {

    private val tag = this::class.java.simpleName

    private var threadProfile: ThreadProfile = ThreadProfile()

    @Inject
    lateinit var ktorClient: KtorClient

    @Inject
    lateinit var jsonUtil: JsonUtil

    @Inject
    lateinit var preferences: DataStorePreferences

    @Inject
    lateinit var userUseCase: UserUseCase

    private var isServiceRunning = true

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onCreate() {
        super.onCreate()

        threadProfile.initComponent(
            context = this@ProfileService,
            jsonUtil = jsonUtil,
            ktorClient = ktorClient,
            preferences = preferences,
            useCase = userUseCase
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.e(tag, "ProfileService is running...")
        Thread {
            while (isServiceRunning) {
                try {
                    threadProfile.getTokenFirebase()
                    Thread.sleep(2000)
                    setupCheckToken()
                } catch (e: InterruptedException) {
                    Log.e(tag, "ProfileService is error : ${e.message}")
                    e.printStackTrace()
                }
            }
        }.start()

        return START_STICKY
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun setupCheckToken() {
        coroutineScope.launch {
            if (threadProfile.getUser().isNotEmpty()){
                if (threadProfile.getToken().isNotEmpty()) {
                    isServiceRunning = false
                    stopSelf()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }

}