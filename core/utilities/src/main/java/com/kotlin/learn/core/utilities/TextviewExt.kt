package com.kotlin.learn.core.utilities

import android.widget.TextView

fun TextView.setTextAnimation(text: String, duration: Long = 200, completion: (() -> Unit)? = null) {
    fadOutAnimation(duration) {
        this.text = text
        fadInAnimation(duration) {
            completion?.let {
                it()
            }
        }
    }
}