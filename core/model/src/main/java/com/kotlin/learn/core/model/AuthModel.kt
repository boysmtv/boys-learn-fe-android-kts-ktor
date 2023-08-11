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
    @Json(name = "id") var id: String = Constant.EMPTY_STRING,
    @Json(name = "idFireStore") var idFireStore: String = Constant.EMPTY_STRING,
    @Json(name = "idGoogle") var idGoogle: String = Constant.EMPTY_STRING,
    @Json(name = "idToken") var idToken: String = Constant.EMPTY_STRING,
    @Json(name = "firstName") var firstName: String = Constant.EMPTY_STRING,
    @Json(name = "lastName") var lastName: String = Constant.EMPTY_STRING,
    @Json(name = "displayName") var displayName: String = Constant.EMPTY_STRING,
    @Json(name = "phone") var phone: String = Constant.EMPTY_STRING,
    @Json(name = "email") var email: String = Constant.EMPTY_STRING,
    @Json(name = "password") var password: String = Constant.EMPTY_STRING,
    @Json(name = "photoUrl") var photoUrl: String = Constant.EMPTY_STRING,
    @Json(name = "method") var method: String = Constant.EMPTY_STRING,
)