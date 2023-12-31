package com.kotlin.learn.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("code") var code: Int? = null,
    @SerialName("status") var status: String? = null,
    @SerialName("data") var data: T? = null
)