package com.kotlin.learn

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.kotlin.learn.core.common.base.BaseActivity
import com.kotlin.learn.core.common.util.LocationUtil
import com.kotlin.learn.core.common.util.ServiceUtil
import com.kotlin.learn.core.common.util.listener.EventListener
import com.kotlin.learn.core.common.data.preferences.DataStorePreferences
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialog
import com.kotlin.learn.core.ui.util.setupLightMode
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.databinding.ActivityMainBinding
import com.kotlin.learn.feature.services.heartbeat.HeartbeatService
import com.kotlin.learn.feature.services.location.LocationService
import com.kotlin.learn.feature.services.profile.ProfileService
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), EventListener {

    private val tag = this::class.java.simpleName

    private lateinit var navHostFragment: NavHostFragment

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    private lateinit var serviceUtil: ServiceUtil

    private lateinit var locationUtil: LocationUtil

    private val permissionIdLocation = 1001

    private var scenarioOnBack: String? = Constant.EMPTY_STRING

    override fun initBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        setupAppCenter()
        setupInit()
        setupLightMode()
        startProfileService()
        startHeartbeatService()
        askNotificationPermission()
        askLocationPermission()
        //checkDrawOverlayPermission()
    }

    override fun backToLogin() {
        supportActionBar?.hide()
        navHostFragment.findNavController().popBackStack(R.id.greetingsFragment, false)
    }

    override fun backToSplash() {
        supportActionBar?.hide()
    }

    override fun showBackButton(isShow: Boolean) {
        if (isShow) supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showTopBar(isShow: Boolean) {
        if (isShow) supportActionBar?.show()
        else supportActionBar?.hide()
    }

    override fun setTopBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onBackNavigation(scenario: String?) {
        scenarioOnBack = scenario
    }

    private fun setupInit() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        serviceUtil = ServiceUtil(context = this)
        locationUtil = LocationUtil(context = this)
    }

    private fun startProfileService() {
        if (!serviceUtil.isRunning(ProfileService::class.java)) {
            val service = Intent(this, ProfileService::class.java)
            startService(service)
        } else {
            Timber.tag(tag).e("ProfileService: service can't started")
        }
    }

    private fun startHeartbeatService() {
        if (!serviceUtil.isRunning(HeartbeatService::class.java)) {
            val service = Intent(this, HeartbeatService::class.java)
            startService(service)
        } else {
            Timber.tag(tag).e("HeartbeatService: service can't started")
        }
    }

    private fun startLocationService() {
        if (!serviceUtil.isRunning(LocationService::class.java)) {
            val service = Intent(this, LocationService::class.java)
            startService(service)
        } else {
            Timber.tag(tag).e("LocationService: service can't started")
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // TODO: FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun requestPermissions() {
        locationPermissionRequest.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // TODO: Precise location access granted.
            }

            permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // TODO: Only approximate location access granted.
            }

            else -> {
                // TODO: No location access granted.
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionIdLocation) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                startLocationService()
            }
        }
    }

    private fun checkDrawOverlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            val content = BaseDataDialog(
                title = "Warning",
                content = "Please allow overlay to continue playing the trailer",
                primaryButtonShow = true,
                secondaryButtonText = Constant.EMPTY_STRING,
                secondaryButtonShow = false,
                icon = com.kotlin.learn.feature.auth.R.drawable.ic_warning_rounded,
                primaryButtonText = "OK"
            )
            showDialogWithActionButton(
                dataToDialog = content,
                actionClickPrimary = {
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                    someActivityResultLauncher.launch(intent)
                },
                actionClickSecondary = {

                },
                tag = MainActivity::class.simpleName.toString()
            )
            Timber.tag(tag).e("Permission request overlay, $packageName")
        } else {
            Timber.tag(tag).e("Permission already granted, $packageName")
        }
    }

    private var someActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            data?.let {
                Timber.tag(tag).e("Permission already granted, data: ${it.data}")
            }
            if (Settings.canDrawOverlays(this)) {
                Timber.tag(tag).e("Permission already granted")
            }
        } else {
            checkDrawOverlayPermission()
            Timber.tag(tag).e("Failed request overlay: ${result.resultCode}")
        }
    }

    override fun askLocationPermission() {
        if (locationUtil.checkPermissions() && locationUtil.isLocationEnabled())
            startLocationService() else requestPermissions()
    }

    private fun setupAppCenter() {
        AppCenter.configure(application, "ee64823d-88bd-4015-bac7-f2f8c1f1ca5d")
        if (AppCenter.isConfigured()) {
            AppCenter.start(Analytics::class.java)
            AppCenter.start(Crashes::class.java)
        }
    }

}