package com.kotlin.learn.core.common.util.event

sealed interface DataStoreCacheEvent<out T> {

    object StoreSuccess : DataStoreCacheEvent<Nothing>
    object FetchError : DataStoreCacheEvent<Nothing>
    class FetchSuccess<T>(val auth: T) : DataStoreCacheEvent<T>

}

fun <T> invokeDataStoreEvent(
    event: DataStoreCacheEvent<T>,
    isFetched: (T) -> Unit,
    isError: () -> Unit,
    isStored: () -> Unit,
) {
    when (event) {
        is DataStoreCacheEvent.StoreSuccess -> {
            isStored.invoke()
        }

        is DataStoreCacheEvent.FetchSuccess -> {
            isFetched.invoke(event.auth)
        }

        is DataStoreCacheEvent.FetchError -> {
            isError.invoke()
        }
    }
}