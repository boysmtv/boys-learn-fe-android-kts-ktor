package com.kotlin.learn.core.model

import com.kotlin.learn.core.utilities.Constant
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

@Serializable
data class AuthGoogleSignInModel(
    val account: String? = Constant.EMPTY_STRING,
    val id: String? = Constant.EMPTY_STRING,
    val idToken: String? = Constant.EMPTY_STRING,
    val email: String? = Constant.EMPTY_STRING,
    val givenName: String? = Constant.EMPTY_STRING,
    val familyName: String? = Constant.EMPTY_STRING,
    val displayName: String? = Constant.EMPTY_STRING,
    val isExpired: String? = Constant.EMPTY_STRING,
    val photoUrl: String? = Constant.EMPTY_STRING,
    val grantedScopes: String? = Constant.EMPTY_STRING,
)