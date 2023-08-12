package com.kotlin.learn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.kotlin.learn.core.common.util.isMyServiceRunning
import com.kotlin.learn.feature.services.ProfileService
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val tag = this::class.java.simpleName

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var profileService: ProfileService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        setupProfileService()
    }

    private fun setupProfileService(){
        profileService = ProfileService()
        profileService.startService(this@MainActivity, "Thread Profile")

//        val intentService = Intent(this@MainActivity, profileService::class.java)
//        if (!isMyServiceRunning(this@MainActivity, profileService::class.java)) {
//            startService(intentService)
//            Timber.tag(tag).e("intentService: start")
//        } else {
//            Timber.tag(tag).e("intentService: can't started")
//        }
    }

}