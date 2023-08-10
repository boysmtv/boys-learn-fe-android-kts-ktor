package com.kotlin.learn.core.common.util.network

import com.kotlin.learn.core.model.BaseResponse
import kotlinx.coroutines.flow.flow

sealed interface ResultSpring<out T> {
    data class Success<T>(val data: T) : ResultSpring<T>
    data class Exception<T>(val data: T) : ResultSpring<T>
    data class Error(val throwable: Throwable) : ResultSpring<Nothing>
    object Loading : ResultSpring<Nothing>
}

inline fun <T> executeSpring(body: () -> T): ResultSpring<T> {
    return try {
        Result.Loading
        ResultSpring.Success(body.invoke())
    } catch (e: Exception) {
        ResultSpring.Exception(body.invoke())
    }
}

sealed interface SpringParser<out T> {
    class Success<T>(val data: T) : SpringParser<T>
    class Error<T>(val data: T) : SpringParser<T>
}

fun <T> invokeSpringParser(
    response: BaseResponse<T>,
) = flow {
    if (response.data != null && response.code == 200) {
        emit(SpringParser.Success(response.data))
    } else {
        emit(SpringParser.Error(response.data))
    }
}