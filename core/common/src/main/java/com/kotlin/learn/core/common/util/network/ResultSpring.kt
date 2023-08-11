package com.kotlin.learn.core.common.util.network

import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.model.BaseResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

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

fun parserResultError(
    throwable: Throwable
) = flow {
    val jsonUtil = JsonUtil(
        moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    )
    try {
        val jsonError = jsonUtil.fromJson<BaseResponse<String>>(throwable.message.toString())
        if (jsonError != null) {
            if (jsonError.data != "" && jsonError.data != null)
                emit(SpringParser.Success(jsonError.data))
            else
                emit(SpringParser.Error(throwable.message.toString()))
        } else
            emit(SpringParser.Error(throwable.message.toString()))
    } catch (throwable: Throwable) {
        emit(SpringParser.Error(throwable.message.toString()))
    }
}
