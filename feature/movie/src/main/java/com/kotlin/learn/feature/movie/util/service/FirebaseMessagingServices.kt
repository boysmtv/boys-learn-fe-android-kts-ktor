package com.kotlin.learn.feature.movie.util.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.feature.movie.R
import java.net.URL

class FirebaseMessagingServices : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        getSharedPreferences(
            "PREFERENCE_NAME", Context.MODE_PRIVATE
        ).edit().putString(
            PreferenceConstants.Authorization.PREF_FCM_TOKEN, token
        ).apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val tag = "FirebaseMessagingService"
        Log.e(tag, "remoteMessage from: ${remoteMessage.from}")
        Log.e(tag, "remoteMessage title: ${remoteMessage.notification?.title}")
        Log.e(tag, "remoteMessage body: ${remoteMessage.notification?.body}")
        showNotification(remoteMessage)
    }

    private fun showNotification(remoteMessage: RemoteMessage) {
        var bitmapImage: Bitmap? = null
        try {
            bitmapImage = BitmapFactory.decodeStream(
                URL("https://selectra.in/sites/selectra.in/files/2021-04/mobile-recharge-plans.png").openStream()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("showNotification", "Error: ${e.message}")
        }
        val pendingIntent: PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.getActivity(this, 0, Intent(), PendingIntent.FLAG_MUTABLE)
        else PendingIntent.getActivity(this, 0, Intent(), PendingIntent.FLAG_UPDATE_CURRENT)

        val channelId = "Default"
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher_discover)
            .setContentTitle(remoteMessage.data["title"])
            .setContentText(remoteMessage.data["body"])
            .setLargeIcon(bitmapImage)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmapImage))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
        manager.notify(0, builder.build())
    }

}