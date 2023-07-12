package com.kotlin.learn.catalog.core.model

import com.kotlin.learn.catalog.core.utilities.Constant
import kotlinx.serialization.Serializable

@Serializable
data class AuthReqModel(
    val username: String? = Constant.EMPTY_STRING,
    val password: String? = Constant.EMPTY_STRING
)

@Serializable
data class AuthRespModel(
    val id: Int? = Constant.ZERO,
    val firstname: String? = Constant.EMPTY_STRING,
    val lastname: String? = Constant.EMPTY_STRING
)