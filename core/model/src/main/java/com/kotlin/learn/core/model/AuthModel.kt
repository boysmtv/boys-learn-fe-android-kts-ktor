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

@JsonClass(generateAdapter = true)
data class
AuthGoogleSignInModel(
    @Json(name = "firebaseId") var firebaseId: String = Constant.EMPTY_STRING,
    @Json(name = "id") val id: String = Constant.EMPTY_STRING,
    @Json(name = "idToken") val idToken: String = Constant.EMPTY_STRING,
    @Json(name = "email") val email: String = Constant.EMPTY_STRING,
    @Json(name = "givenName") val givenName: String = Constant.EMPTY_STRING,
    @Json(name = "familyName") val familyName: String = Constant.EMPTY_STRING,
    @Json(name = "displayName") val displayName: String = Constant.EMPTY_STRING,
    @Json(name = "isExpired") val isExpired: String = Constant.EMPTY_STRING,
    @Json(name = "photoUrl") val photoUrl: String = Constant.EMPTY_STRING,
    @Json(name = "grantedScopes") val grantedScopes: String = Constant.EMPTY_STRING,
)