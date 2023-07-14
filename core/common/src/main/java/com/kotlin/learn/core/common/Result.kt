package com.kotlin.learn.core.common

import android.content.res.Resources.NotFoundException
import androidx.paging.PagingSource

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val throwable: Throwable) : Result<Nothing>
    object Loading : Result<Nothing>
}

inline fun <T> executeWithResponse(body: () -> T): Result<T> {
    return try {
        Result.Loading
        Result.Success(body.invoke())
    } catch (throwable: Throwable){
        Result.Error(throwable)
    }
}


val Result<*>?.isInitialState get() = this != null && this is Result.Success && data == null

val Result<*>?.isSucceeded get() = this != null && this is Result.Success && data != null

val Result<*>?.isError get() = this != null && this is Result.Error

val Result<*>?.isLoading get() = this != null && this is Result.Loading


inline infix fun <T, Value : Any> Result<T>?.runSucceeded(predicate: (data: T) -> Value): Value? {
    if (this != null && this.isSucceeded && this is Result.Success && this.data != null) {
        return predicate.invoke(this.data)
    }
    return null
}

inline infix fun <T> Result<T>.success(predicate: (data: T) -> Unit): Result<T> {
    if (this is Result.Success && this.data != null) {
        predicate.invoke(this.data)
    }
    return this
}

inline infix fun <T> Result<T>.error(predicate: (data: Throwable) -> Unit) {
    if (this is Result.Error) {
        predicate.invoke(this.throwable)
    }
}

inline infix fun <T, Value : Any> Result<T>.pagingSucceeded(
    predicate: (data: T) -> PagingSource.LoadResult<Int, Value>
): PagingSource.LoadResult<Int, Value> {
    return if (this is Result.Success && this.data != null) {
        predicate.invoke(this.data)
    } else {
        if (this is Result.Error) {
            PagingSource.LoadResult.Error(this.throwable)
        } else {
            PagingSource.LoadResult.Error(NotFoundException())
        }
    }
}