package com.kotlin.learn.core.utilities.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

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
    state: Lifecycle.State = Lifecycle.State.RESUMED,
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
                Timber.tag("launchWhenStarted").e("Error-try-collect : ${t.message}")
            }
        }
    }
}

