package com.kotlin.learn.core.common.util

sealed class DataStoreCacheEvent {

    object StoreSuccess : DataStoreCacheEvent()
    class FetchSuccess(val auth: String) : DataStoreCacheEvent()

}