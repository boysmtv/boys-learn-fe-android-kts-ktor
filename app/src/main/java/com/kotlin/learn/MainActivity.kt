package com.kotlin.learn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.kotlin.learn.core.common.util.isMyServiceRunning
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.feature.services.ProfileService
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val tag = this::class.java.simpleName

    private lateinit var navHostFragment: NavHostFragment

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    private lateinit var profileService: ProfileService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navHostFragment

        setupProfileService()
    }

    private fun setupProfileService() {
        profileService = ProfileService()

        val service = Intent(this@MainActivity, profileService::class.java)
        if (!isMyServiceRunning(this@MainActivity, profileService::class.java)) {
            startService(service)
        } else {
            Timber.tag(tag).e("profileService: service can't started")
        }
    }

}