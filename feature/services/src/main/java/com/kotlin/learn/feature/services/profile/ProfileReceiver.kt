package com.kotlin.learn.feature.services.profile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class ProfileReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            val serviceIntent = Intent(
                context,
                ProfileService::class.java
            )
            context!!.startForegroundService(serviceIntent)
        }
    }

}