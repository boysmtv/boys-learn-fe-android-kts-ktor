package com.kotlin.learn.core.model

import com.kotlin.learn.core.utilities.Constant
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
data class AuthReqModel(
    val id: String = Constant.EMPTY_STRING,
    val table: String = Constant.EMPTY_STRING,
    val data: String = Constant.EMPTY_STRING
)

@Serializable
data class AuthRespModel(
    val id: Int? = Constant.ZERO,
    val firstname: String? = Constant.EMPTY_STRING,
    val lastname: String? = Constant.EMPTY_STRING
)


@Serializable
@JsonClass(generateAdapter = true)
data class UserModel(
    @Json(name = "id") var id: String? = null,
    @Json(name = "idFireStore") var idFireStore: String? = null,
    @Json(name = "idGoogle") var idGoogle: String? = null,
    @Json(name = "idToken") var idToken: String? = null,
    @Json(name = "firstName") var firstName: String? = null,
    @Json(name = "lastName") var lastName: String? = null,
    @Json(name = "displayName") var displayName: String? = null,
    @Json(name = "phone") var phone: String? = null,
    @Json(name = "email") var email: String? = null,
    @Json(name = "password") var password: String? = null,
    @Json(name = "photoUrl") var photoUrl: String? = null,
    @Json(name = "method") var method: String? = null,
)