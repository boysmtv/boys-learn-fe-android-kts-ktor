@file:Suppress("DEPRECATION")

package com.kotlin.learn.core.common.util

import android.app.ActivityManager
import android.content.Context

class ServiceUtil(private val context: Context) {

    private val tag = this::class.java.simpleName

    fun isRunning(serviceClass: Class<*>): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

}