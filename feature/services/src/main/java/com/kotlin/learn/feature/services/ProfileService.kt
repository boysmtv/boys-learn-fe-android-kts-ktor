package com.kotlin.learn.feature.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.text.TextUtils
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.domain.UserUseCase
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.feature.services.common.ThreadProfile
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileService : Service() {

    private val tag = this::class.java.simpleName

    private var threadProfile: ThreadProfile = ThreadProfile()

    @Inject
    lateinit var ktorClient: KtorClient

    @Inject
    lateinit var jsonUtil: JsonUtil

    @Inject
    lateinit var preferences: DataStorePreferences

    @Inject
    lateinit var userUseCase: UserUseCase

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        threadProfile.initComponent(
            context = this@ProfileService,
            jsonUtil = jsonUtil,
            ktorClient = ktorClient,
            preferences = preferences,
            useCase = userUseCase
        )
        threadProfile.getTokenFirebase()

        onRestart(intent)

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
                            threadProfile.getTokenFirebase()
                        }
                    }.start()
                    Log.e(tag, "onStartCommand - Booting Completed")
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }

}