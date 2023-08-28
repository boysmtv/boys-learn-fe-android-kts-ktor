package com.kotlin.learn.feature.services.location

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import java.util.Timer
import java.util.TimerTask

class LocationService : Service(), LocationListener {

    private val TAG = "BookingTrackingService"
    private var context: Context? = null
    private var isGPSEnable = false
    private var isNetworkEnable = false
    private var latitude = 0.0
    private var longitude = 0.0
    private var locationManager: LocationManager? = null
    private var location: Location? = null
    private var mTimer: Timer? = null
    private val mHandler: Handler = Handler()
    private var notify_interval: Long = 30000

    private var track_lat = 0.0
    private var track_lng = 0.0
    private var str_receiver = "servicetutorial.service.receiver"

    private var intent: Intent? = null

    override fun onCreate() {
        super.onCreate()

        mTimer = Timer()
        mTimer!!.schedule(TimerTaskToGetLocation(), 5, notify_interval)
        intent = Intent(str_receiver)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        context = this
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onLocationChanged(location: Location) {
        track_lat = location.latitude
        track_lng = location.longitude
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy <<")
        if (mTimer != null) {
            mTimer!!.cancel()
        }
    }

    private fun trackLocation() {
        Log.e(TAG, "trackLocation")
        val params: MutableMap<String, String> = HashMap()
        params["latitude"] = "" + track_lat
        params["longitude"] = "" + track_lng
        Log.e(TAG, "param_track_location >> $params")
        stopSelf()
        mTimer!!.cancel()
    }

    @Deprecated("Deprecated in Java")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    /** */
    private fun fnGetlocation() {
        locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
        isGPSEnable = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        isNetworkEnable = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isGPSEnable && !isNetworkEnable) {
            Log.e(TAG, "CAN'T GET LOCATION")
            stopSelf()
        } else {
            if (isNetworkEnable) {
                location = null
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0f, this)
                if (locationManager != null) {
                    location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (location != null) {
                        Log.e(
                            TAG, """
     isNetworkEnable latitude${location!!.latitude}
     longitude${location!!.longitude}
     """.trimIndent()
                        )
                        latitude = location!!.latitude
                        longitude = location!!.longitude
                        track_lat = latitude
                        track_lng = longitude
                        //                        fn_update(location);
                    }
                }
            }
            if (isGPSEnable) {
                location = null
                locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0f, this)
                if (locationManager != null) {
                    location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (location != null) {
                        Log.e(
                            TAG, """
     isGPSEnable latitude${location!!.latitude}
     longitude${location!!.longitude}
     """.trimIndent()
                        )
                        latitude = location!!.latitude
                        longitude = location!!.longitude
                        track_lat = latitude
                        track_lng = longitude
                        //                        fn_update(location);
                    }
                }
            }
            Log.e(TAG, "START SERVICE")
            trackLocation()
        }
    }

    private fun fnUpdate(location: Location) {
        intent!!.putExtra("latutide", location.latitude.toString() + "")
        intent!!.putExtra("longitude", location.longitude.toString() + "")
        sendBroadcast(intent)
    }

    private inner class TimerTaskToGetLocation : TimerTask() {
        override fun run() {
            mHandler.post { fnGetlocation() }
        }
    }

}