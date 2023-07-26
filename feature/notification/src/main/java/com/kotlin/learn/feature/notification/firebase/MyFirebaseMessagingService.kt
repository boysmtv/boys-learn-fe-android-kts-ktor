package com.kotlin.learn.feature.notification.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kotlin.learn.feature.notification.R
import java.net.URL

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "FirebaseMessagingService"

    override fun onNewToken(token: String) {
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "remoteMessage from: ${remoteMessage.from}")
        Log.d(TAG, "remoteMessage title: ${remoteMessage.notification?.title}")
        Log.d(TAG, "remoteMessage body: ${remoteMessage.notification?.body}")
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

    private fun sendRegistrationToServer(token: String) {
        Log.e("FCM TOKEN", token);
    }
}