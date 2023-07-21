package com.kotlin.learn.core.common.util

sealed class DataStoreCacheEvent {

    object StoreSuccess : DataStoreCacheEvent()
    class FetchSuccess(val auth: String) : DataStoreCacheEvent()

}

fun invokeDataStoreEvent(
    event: DataStoreCacheEvent,
    isFetched: (String) -> Unit,
    isStored: () -> Unit,
) {
    when (event) {
        is DataStoreCacheEvent.StoreSuccess -> {
            isStored.invoke()
        }

        is DataStoreCacheEvent.FetchSuccess -> {
            isFetched.invoke(event.auth)
        }
    }
}