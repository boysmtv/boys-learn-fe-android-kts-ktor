package com.kotlin.learn.catalog.core.common

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