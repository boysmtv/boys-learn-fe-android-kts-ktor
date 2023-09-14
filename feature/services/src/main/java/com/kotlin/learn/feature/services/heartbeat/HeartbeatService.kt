package com.kotlin.learn.feature.services.heartbeat

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.kotlin.learn.core.common.util.DeviceUtil
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.TransactionUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.domain.HeartbeatUseCase
import com.kotlin.learn.core.model.HeartbeatModel
import com.kotlin.learn.core.model.LocationModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class HeartbeatService : Service() {

    private val tag = this::class.java.simpleName

    private var threadHeartbeat: ThreadHeartbeat = ThreadHeartbeat()

    @Inject
    lateinit var jsonUtil: JsonUtil

    @Inject
    lateinit var preferences: DataStorePreferences

    @Inject
    lateinit var heartbeatUseCase: HeartbeatUseCase

    private val threadSleepTimer = 3000000L

    override fun onCreate() {
        super.onCreate()

        threadHeartbeat.initComponent(
            context = this@HeartbeatService,
            jsonUtil = jsonUtil,
            preferences = preferences,
            useCase = heartbeatUseCase
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread {
            while (true) {
                try {
                    threadHeartbeat.storeHeartbeat(
                        HeartbeatModel().apply {
                            deviceId = DeviceUtil.getDeviceID(this@HeartbeatService)
                            deviceName = DeviceUtil.getDeviceName()
                            location = runBlocking { threadHeartbeat.getLocation() }
                            createdAt = TransactionUtil.getTimestampWithFormat()
                        }
                    )
                    Thread.sleep(threadSleepTimer)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }

}