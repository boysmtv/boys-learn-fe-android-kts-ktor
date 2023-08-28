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
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.kotlin.learn.core.common.util.LocationUtil

class LocationService : Service(), LocationListener {

    private lateinit var locationUtil: LocationUtil
    private var location: Location? = null

    private val tag = this::class.java.simpleName

    private var trackLatitude = 0.0
    private var trackLongitude = 0.0

    override fun onCreate() {
        super.onCreate()
        locationUtil = LocationUtil(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.e("Service", "LocationService is running...")
        Thread {
            while (true) {
                try {
                    getLocation()
                    Thread.sleep(60000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onLocationChanged(location: Location) {
        trackLatitude = location.latitude
        trackLongitude = location.longitude
        Log.e(tag, "onLocationChanged - latitude: $trackLatitude")
        Log.e(tag, "onLocationChanged - longitude: $trackLongitude")
    }

    private fun getLocation() {
        val locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
        val isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGPSEnable && !isNetworkEnable) {
            stopSelf()
        } else {
            if (isNetworkEnable) {
                getLocationFromNetwork(locationManager)
            }
            if (isGPSEnable) {
                getLocationFromGps(locationManager)
            }
        }
    }

    private fun getLocationFromNetwork(locationManager: LocationManager) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        Handler(Looper.getMainLooper()).postDelayed({
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0f, this)
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            location?.let {
                trackLatitude = it.latitude
                trackLongitude = it.longitude
                Log.e(tag, "getFromNetwork - latitude: $trackLatitude")
                Log.e(tag, "getFromNetwork - longitude: $trackLongitude")
            }
        }, 0)
    }

    private fun getLocationFromGps(locationManager: LocationManager) {
        location = null
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        Handler(Looper.getMainLooper()).postDelayed({
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0f, this)
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            location?.let {
                trackLatitude = it.latitude
                trackLongitude = it.longitude
                Log.e(tag, "getFromGps - latitude: $trackLatitude")
                Log.e(tag, "getFromGps - longitude: $trackLongitude")
            }
        }, 0)
    }

    @Deprecated("Deprecated in Java")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        // TODO : do status changed
    }

}