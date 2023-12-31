package com.kotlin.learn.feature.movie.presentation.ui

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.feature.movie.databinding.FragmentVideoPlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.launch

class VideoPlayerFragment : BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {

    private val args: VideoPlayerFragmentArgs by navArgs()

    override fun setupView() {
        loadArguments()
        forcePortrait()
    }

    private fun loadArguments() = with(binding) {
        lifecycle.addObserver(youtubePlayer)
        lifecycle.coroutineScope.launch {
            youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(args.keyId, 0f)
                }
            })
        }
    }

    private fun forcePortrait() {
        val a: Activity? = activity
        if (a != null) a.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

}