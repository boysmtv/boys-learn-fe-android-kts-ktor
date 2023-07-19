package com.kotlin.learn.core.common.util

sealed class DataStoreCacheEvent {

    object StoreSuccess : DataStoreCacheEvent()
    class FetchSuccess(val auth: String) : DataStoreCacheEvent()

}

fun invokeDataStoreEvent(
    event: DataStoreCacheEvent,
    isSuccessStore: () -> Unit,
    isSuccessFetch: (auth: String) -> Unit,
) {
    when (event) {
        is DataStoreCacheEvent.StoreSuccess -> {
            isSuccessStore.invoke()
        }

        is DataStoreCacheEvent.FetchSuccess -> {
            isSuccessFetch.invoke(event.auth)
        }
    }
}