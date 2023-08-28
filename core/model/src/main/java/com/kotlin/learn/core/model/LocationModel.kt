package com.kotlin.learn.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationModel(
    @SerialName("latitude") var latitude: Double = 0.0,
    @SerialName("longitude") var longitude: Double = 0.0,
)