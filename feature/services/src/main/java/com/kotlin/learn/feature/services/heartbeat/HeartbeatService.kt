package com.kotlin.learn.feature.services.heartbeat

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.kotlin.learn.core.common.util.Device
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.domain.HeartbeatUseCase
import com.kotlin.learn.core.model.HeartbeatModel
import com.kotlin.learn.core.model.LocationModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
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

    private val threadSleepTimer = 60000L

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

        Log.e("Service", "HeartbeatService is running...")
        Thread {
            while (true) {
                try {
                    threadHeartbeat.storeHeartbeat(
                        HeartbeatModel().apply {
                            deviceId = Device.getDeviceID(this@HeartbeatService)
                            deviceName = Device.getDeviceName()
                            location = fetchLocation()
                            createdAt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
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

    private fun fetchLocation(): LocationModel {
        val strLocation = runBlocking { threadHeartbeat.getLocation() }
        if (strLocation.isNotEmpty()) {
            jsonUtil.fromJson<LocationModel>(strLocation)?.let {
                return it
            }
            return LocationModel()
        } else
            return LocationModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }

}