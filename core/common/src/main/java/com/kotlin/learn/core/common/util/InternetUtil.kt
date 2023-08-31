package com.kotlin.learn.core.common.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kotlin.learn.core.model.ConnectionModel


class InternetUtil(private val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }


    fun getStatusConnectionModel(): ConnectionModel {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return ConnectionModel()
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return ConnectionModel()
        return ConnectionModel().apply {
            wifi = actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            mobile = actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            bluetooth = actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)
            ethernet = actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }
    }

}