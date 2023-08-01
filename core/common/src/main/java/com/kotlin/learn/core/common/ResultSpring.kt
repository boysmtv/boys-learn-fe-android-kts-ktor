package com.kotlin.learn.core.common

import com.kotlin.learn.core.model.BaseResponse
import kotlinx.coroutines.flow.flow

sealed interface SpringResult<out T> {
    data class Success<T>(val data: T) : SpringResult<T>
    data class Error(val throwable: Throwable) : SpringResult<Nothing>
    object Loading : SpringResult<Nothing>
}

inline fun <T> springResultExecute(body: () -> T): SpringResult<T> {
    return try {
        SpringResult.Loading
        SpringResult.Success(body.invoke())
    } catch (throwable: Throwable) {
        SpringResult.Error(throwable)
    }
}

sealed interface SpringResultResponse<out T> {
    class Success<T>(val data: T) : SpringResultResponse<T>
    class Error<T>(val data: T) : SpringResultResponse<T>
}

fun <T> invokeSpringResultResponse(
    response: BaseResponse<T>,
) = flow {
    if (response.data != null && response.code == 200) {
        emit(SpringResultResponse.Success(response.data))
    } else {
        emit(SpringResultResponse.Error(response.data))
    }
}