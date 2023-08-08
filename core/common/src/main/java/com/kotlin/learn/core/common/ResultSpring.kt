package com.kotlin.learn.core.common

import com.kotlin.learn.core.model.BaseResponse
import kotlinx.coroutines.flow.flow

sealed interface Spring<out T> {
    data class Success<T>(val data: T) : Spring<T>
    data class Error(val throwable: Throwable) : Spring<Nothing>
    object Loading : Spring<Nothing>
}

inline fun <T> executeSpring(body: () -> T): Spring<T> {
    return try {
        Spring.Loading
        Spring.Success(body.invoke())
    } catch (throwable: Throwable) {
        Spring.Error(throwable)
    }
}

sealed interface SpringResult<out T> {
    class Success<T>(val data: T) : SpringResult<T>
    class Error<T>(val data: T) : SpringResult<T>
}

fun <T> invokeSpringResult(
    response: BaseResponse<T>,
) = flow {
    if (response.data != null && response.code == 200) {
        emit(SpringResult.Success(response.data))
    } else {
        emit(SpringResult.Error(response.data))
    }
}