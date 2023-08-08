package com.kotlin.learn.core.model

import com.kotlin.learn.core.utilities.Constant
import kotlinx.serialization.Serializable

@Serializable
data class LoginReqModel(
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING
)

@Serializable
data class LoginRespModel(
    val id: String? = null,
    val fullName: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)