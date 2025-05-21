package com.kotlin.learn.core.common.util

import android.content.Context
import android.graphics.Bitmap
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.kotlin.learn.core.common.R

object ImageUtil {

    fun imageViewAnimatedChange(context: Context?, imageView: ImageView, newImage: Bitmap?) {
        val animOut: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val animIn: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // no needed yet
            }

            override fun onAnimationRepeat(animation: Animation) {
                // no needed yet
            }

            override fun onAnimationEnd(animation: Animation) {
                imageView.setImageBitmap(newImage)
                animIn.setAnimationListener(object : AnimationListener {
                    override fun onAnimationStart(animation: Animation) {
                        // no needed yet
                    }

                    override fun onAnimationRepeat(animation: Animation) {
                        // no needed yet
                    }

                    override fun onAnimationEnd(animation: Animation) {
                        // no needed yet
                    }
                })
                imageView.startAnimation(animIn)
            }
        })
        imageView.startAnimation(animOut)
    }

}