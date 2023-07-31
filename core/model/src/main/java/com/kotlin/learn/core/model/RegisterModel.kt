package com.kotlin.learn.core.model

import com.kotlin.learn.core.utilities.Constant
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReqModel(
    val firstName: String = Constant.EMPTY_STRING,
    val lastName: String = Constant.EMPTY_STRING,
    val phoneNumber: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING
)

@Serializable
data class RegisterRespModel(
    val id: Int? = Constant.ZERO,
    val fullName: String? = Constant.EMPTY_STRING,
    val createdAt: String? = Constant.EMPTY_STRING,
    val updatedAt: String? = Constant.EMPTY_STRING
)