package com.kotlin.learn.core.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseErrorException(
    val errorCode: Int,   //if by errorCode you mean the http status code is not really necessary to include here as you already know it from the validateResponse
    val errorMessage: String
)