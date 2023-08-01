package com.kotlin.learn.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("code") var code: Int? = null,
    @SerialName("status") var status: String? = null,
    @SerialName("data") var data: T? = null
)


@Serializable
data class BaseError(
    @SerialName("timestamp") var timestamp: String? = null,
    @SerialName("status") var status: Int? = null,
    @SerialName("error") var error: String? = null,
    @SerialName("message") var message: String? = null,
    @SerialName("path") var path: String? = null
)