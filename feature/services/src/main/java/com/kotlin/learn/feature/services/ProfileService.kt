package com.kotlin.learn.feature.services

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.feature.services.common.ThreadProfile
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileService : Service() {

    private val tag = this::class.java.simpleName

    private var threadProfile: ThreadProfile = ThreadProfile()

    @Inject
    lateinit var jsonUtil: JsonUtil

    @Inject
    lateinit var ktorClient: KtorClient

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        object : CountDownTimer(5000, 1000) {
            override fun onTick(l: Long) {}
            override fun onFinish() {
                if (::dataStorePreferences.isInitialized) {
                    threadProfile.initComponent(
                        context = this@ProfileService,
                        dataStorePreferences = dataStorePreferences
                    )
                    threadProfile.postToken()
                    onRestart(intent)
                    Log.e(tag, "mServiceIntent: started")
                } else {
                    stopSelf()
                    Log.e(tag, "mServiceIntent: can't started")
                }
            }
        }.start()

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun onRestart(intent: Intent?) {
        if (intent != null) {
            if (intent.action != null)
                if (intent.action.equals(
                        "android.intent.action.BOOT_COMPLETED",
                        ignoreCase = true
                    )
                ) {
                    object : CountDownTimer(10000, 1000) {
                        override fun onTick(l: Long) {}
                        override fun onFinish() {
                            threadProfile.postToken()
                        }
                    }.start()
                    Log.e(tag, "onStartCommand - Booting Completed")
                }
        }
    }

}