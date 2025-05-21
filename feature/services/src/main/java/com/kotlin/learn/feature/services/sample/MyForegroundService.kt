package com.kotlin.learn.feature.services.sample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import com.kotlin.learn.core.utilities.Constant.ONE_HUNDRED_ONE
import com.kotlin.learn.core.utilities.Constant.THREAD_SLEEP_TIMER_2_SECOND
import com.kotlin.learn.feature.services.R

class MyForegroundService : Service() {
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Thread {
            while (true) {
                try {
                    Thread.sleep(THREAD_SLEEP_TIMER_2_SECOND)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "Foreground Service ID"
            val channel = NotificationChannel(
                channelId,
                channelId,
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
            val notification = Notification.Builder(this, channelId)
                .setContentText("Service Foreground is running")
                .setContentTitle("Service Foreground enabled")
                .setSmallIcon(R.drawable.ic_discover)
            startForeground(ONE_HUNDRED_ONE, notification.build())
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}