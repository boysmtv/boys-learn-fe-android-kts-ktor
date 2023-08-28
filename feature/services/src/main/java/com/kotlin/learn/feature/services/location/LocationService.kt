package com.kotlin.learn.feature.services.location

import android.Manifest
import android.app.Service
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
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.LocationUtil
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.model.LocationModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service(), LocationListener {

    private val tag = this::class.java.simpleName

    private var threadLocation: ThreadLocation = ThreadLocation()

    @Inject
    lateinit var jsonUtil: JsonUtil

    @Inject
    lateinit var preferences: DataStorePreferences

    private lateinit var locationUtil: LocationUtil
    private var location: Location? = null

    private var locationModel = LocationModel()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    private val threadSleepTimer = 59000L

    override fun onCreate() {
        super.onCreate()

        locationUtil = LocationUtil(context = this)
        threadLocation.initComponent(context = this, jsonUtil = jsonUtil, preferences = preferences)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.e("Service", "LocationService is running...")
        Thread {
            while (true) {
                try {
                    getLocation()
                    Thread.sleep(threadSleepTimer)
                    setupCheckLocation()
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
        locationModel.apply {
            latitude = location.latitude
            longitude = location.longitude
        }
        threadLocation.storeLocation(locationModel)
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
                locationModel.apply {
                    latitude = it.latitude
                    longitude = it.longitude
                }
                threadLocation.storeLocation(locationModel)
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
                locationModel.apply {
                    latitude = it.latitude
                    longitude = it.longitude
                }
                threadLocation.storeLocation(locationModel)
            }
        }, 0)
    }

    @Deprecated("Deprecated in Java")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        // TODO : do status changed
    }

    private fun setupCheckLocation() {
        coroutineScope.launch {
            val location = threadLocation.getLocation()
            if (location.isNotEmpty()) {
                val locationModel = jsonUtil.fromJson<LocationModel>(location)
                locationModel?.let {
                    /*Log.e(tag, "setupCheckLocation - latitude: ${it.latitude}")
                    Log.e(tag, "setupCheckLocation - longitude: ${it.longitude}")*/
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }

}