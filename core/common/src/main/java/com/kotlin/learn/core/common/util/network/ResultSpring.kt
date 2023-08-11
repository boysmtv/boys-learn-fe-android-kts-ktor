package com.kotlin.learn.core.common.util.network

import com.kotlin.learn.core.model.BaseResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type

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

fun parseResultError(
    throwable: Throwable
) = flow {
    val jsonType: Type = Types.newParameterizedType(BaseResponse::class.java, String::class.java)
    val jsonAdapter: JsonAdapter<BaseResponse<String>> = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build().adapter(jsonType)
    try {
        val json = throwable.message?.let { jsonAdapter.fromJson(it) }
        if (json != null) {
            if (json.data != "" && json.data != null)
                emit(SpringParser.Success(json.data))
            else
                emit(SpringParser.Error(throwable.message))
        } else
            emit(SpringParser.Error(throwable.message))
    } catch (throwable: Throwable) {
        emit(SpringParser.Error(throwable.message))
    }
}
