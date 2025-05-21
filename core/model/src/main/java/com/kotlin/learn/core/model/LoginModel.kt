package com.kotlin.learn.core.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRespModel(
    val id: String? = null,
    val fullName: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)