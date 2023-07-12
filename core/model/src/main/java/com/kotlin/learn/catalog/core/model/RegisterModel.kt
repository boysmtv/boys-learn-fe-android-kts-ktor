package com.kotlin.learn.catalog.core.model

import com.kotlin.learn.catalog.core.utilities.Constant
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReqModel(
    val firstName: String = Constant.EMPTY_STRING,
    val lastName: String = Constant.EMPTY_STRING,
    val phone: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING
)

@Serializable
data class RegisterRespModel(
    val id: Int? = Constant.ZERO,
    val fullName: String? = Constant.EMPTY_STRING
)