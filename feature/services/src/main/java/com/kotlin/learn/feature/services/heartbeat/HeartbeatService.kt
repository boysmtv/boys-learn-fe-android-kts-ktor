package com.kotlin.learn.feature.services.heartbeat

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.kotlin.learn.core.common.util.Device

class HeartbeatService : Service() {

    private val tag = this::class.java.simpleName

    private var isServiceRunning = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.e("Service", "HeartbeatService is running...")
        Thread {
            while (isServiceRunning) {
                try {
                    Thread.sleep(5000)
                    Log.e(tag, "This device name is : ${Device.getDeviceName()}")
                    Log.e(tag, "This device id is : ${Device.getDeviceID(this)}")

                    isServiceRunning = false
                    stopSelf()
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

}