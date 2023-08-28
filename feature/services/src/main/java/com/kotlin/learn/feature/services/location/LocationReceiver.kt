package com.kotlin.learn.feature.services.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class LocationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Log.e("LocationReceiver", "You are in LocationReceiver class.")
        val background = Intent(context, LocationService::class.java)
        context.startService(background)
    }

}
