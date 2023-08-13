package com.kotlin.learn.feature.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kotlin.learn.core.common.R
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.utilities.PreferenceConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MessagingService : FirebaseMessagingService() {

    private val tag = this::class.java.simpleName
    private val channelId = "com.kotlin.learn"
    private val channelName = "com.kotlin.learn"
    private val notificationName = "Discover"

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onNewToken(token: String) {
        coroutineScope.launch {
            storeToPreferences(token)
            Log.e(tag, "Token-thread: $token")
            Log.e(tag, "Token-store: ${fetchToPreferences()}")
        }
    }

    private suspend fun storeToPreferences(token: String) {
        withContext(Dispatchers.IO) {
            dataStorePreferences.setString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                token
            )
        }
    }

    private suspend fun fetchToPreferences(): String {
        return withContext(Dispatchers.IO) {
            dataStorePreferences.getString(
                PreferenceConstants.Authorization.PREF_FCM_TOKEN
            ).getOrNull().orEmpty()
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e(tag, "onMessageReceived title: ${remoteMessage.notification?.title}")
        Log.e(tag, "onMessageReceived body: ${remoteMessage.notification?.body}")

        if (remoteMessage.notification != null) {
            showNotification(remoteMessage.notification!!.title, remoteMessage.notification!!.body)
        }
    }

    private fun showNotification(title: String?, message: String?) {
        val pendingIntent = PendingIntent.getActivity(
            this, 0, Intent(),
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelName)
            .setSmallIcon(R.drawable.ic_movie)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getCustomDesign(title, message))
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                channelId, notificationName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager!!.createNotificationChannel(
                notificationChannel
            )
        }
        notificationManager!!.notify(0, builder.build())
    }

    private fun getCustomDesign(
        title: String?,
        message: String?
    ): RemoteViews {
        val remoteViews = RemoteViews(applicationContext.packageName, R.layout.notification_firebase)
        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.description, message)
        remoteViews.setImageViewResource(
            R.id.icon,
            R.drawable.ic_discover
        )
        return remoteViews
    }

}