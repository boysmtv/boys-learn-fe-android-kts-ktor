package com.kotlin.learn

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.kotlin.learn.feature.services.ProfileService
import dagger.hilt.android.AndroidEntryPoint

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

    private fun setupProfileService() {
        object : CountDownTimer(5000, 1000) {
            override fun onTick(l: Long) {
            }

            override fun onFinish() {
                profileService = ProfileService()
                profileService.startService(this@MainActivity, "Thread Profile")

//                val intentService = Intent(this@MainActivity, profileService::class.java)
//                if (!isMyServiceRunning(this@MainActivity, profileService::class.java)) {
//                    startService(intentService)
//                    Timber.tag(tag).e("intentService: start")
//                } else {
//                    Timber.tag(tag).e("intentService: can't started")
//                }
            }
        }.start()
    }

}