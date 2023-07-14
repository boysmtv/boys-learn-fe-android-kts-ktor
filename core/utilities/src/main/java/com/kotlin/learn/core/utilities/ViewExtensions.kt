/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.kotlin.learn.core.utilities

import android.content.Context
import android.content.res.TypedArray
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.kotlin.learn.core.utilities.Constant.ONE
import com.kotlin.learn.core.utilities.listener.SingleClickListener
import com.kotlin.learn.core.utilities.listener.SingleClickListener.Companion.THROTTLE_INTERVAL

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun CheckBox.checked() {
    this.isChecked = true
}

fun CheckBox.unChecked() {
    this.isChecked = false
}

@Deprecated(
    message = "Use Android-KTX built-in function instead",
    replaceWith = ReplaceWith("androidx.core.view.isVisible")
)
fun View.showOrHide(condition: Boolean) {
    if (condition) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

@Deprecated(
    message = "Use Android-KTX built-in function instead",
    replaceWith = ReplaceWith("androidx.core.view.isInvisible")
)
fun View.showOrInvisible(condition: Boolean) {
    if (condition) this.visibility = View.VISIBLE
    else this.visibility = View.INVISIBLE
}

fun View.showForceKeyboard() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (inputMethodManager.isAcceptingText) inputMethodManager.hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun withRecycle(attributes: TypedArray, consumeAttributes: TypedArray.() -> Unit) {
    try {
        consumeAttributes(attributes)
    } finally {
        attributes.recycle()
    }
}

fun View.getString(@StringRes stringId: Int): String = context.getString(stringId)

fun View.setOnSingleClickListener(
    throttleInterval: Long = THROTTLE_INTERVAL,
    click: View.OnClickListener?
) {
    click?.let { setOnSingleClick(throttleInterval, click) } ?: setOnClickListener(null)
}

fun View.setOnSingleClickListener(throttleInterval: Long, listener: (View) -> Unit) {
    this.setOnSingleClickListener(throttleInterval, View.OnClickListener { v -> listener(v) })
}

fun View.setOnSingleClick(throttleInterval: Long, click: View.OnClickListener) =
    setOnClickListener(SingleClickListener(throttleInterval, click))


fun FrameLayout.alreadyScrolled(listener: (Boolean) -> Unit) {
    post { listener(!canScrollVertically(ONE)) }
    setOnScrollChangeListener { _, _, _, _, _ ->
        canScrollVertically(ONE).run { if (!this) listener(true) }
    }
}