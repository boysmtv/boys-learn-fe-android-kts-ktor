/*
 *
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.kotlin.learn.catalog.core.utilities.extension

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
fun <T> MutableStateFlow<T>.launch(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State,
    action: suspend (T) -> Unit
) {
    onEach(action).launchWhenStarted(
        lifecycleOwner,
        state
    )
}

fun <T> Flow<T>.launch(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend (T) -> Unit
) {
    onEach(action).launchWhenStarted(
        lifecycleOwner,
        state
    )
}

fun <T> Flow<T>.launchWhenStarted(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State
) = with(lifecycleOwner) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            try {
                this@launchWhenStarted.collect()
            } catch (t: Throwable) {
                Log.e("Error-Collect", "Value: ${t.message}")
            }
        }
    }
}