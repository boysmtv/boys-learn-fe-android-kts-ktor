package com.kotlin.learn.feature.services.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LocationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val background = Intent(context, LocationService::class.java)
        context.startService(background)
    }

}
