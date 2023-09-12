package com.kotlin.learn.core.common.util.network

sealed interface ResultCallback<out T> {
    data class Success<T>(val data: T) : ResultCallback<T>
    data class Error(val message: String) : ResultCallback<Nothing>
    object Loading : ResultCallback<Nothing>
}