package com.kotlin.learn.feature.services.heartbeat

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kotlin.learn.core.common.util.Device
import java.util.Locale

class HeartbeatService : Service() {

    private val tag = this::class.java.simpleName

    private var isServiceRunning = true
    private var isServiceCount = 0

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread {
            while (isServiceRunning) {
                Log.e("Service", "HeartbeatService is running...")
                try {
                    isServiceCount += 5000
                    if (isServiceCount >= 10000) {
                        isServiceRunning = false
                        stopSelf()
                    }
                    Thread.sleep(5000)
                    Log.e(tag, "This device name is : ${Device.getDeviceName()}")
                    Log.e(tag, "This device id is : ${Device.getDeviceID(this)}")
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