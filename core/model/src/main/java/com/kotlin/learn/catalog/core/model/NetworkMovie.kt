package com.kotlin.learn.catalog.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovie(
    @SerialName("page") var page: Int = 0,
    @SerialName("results") var results: List<NetworkMovieData> = arrayListOf(),
    @SerialName("total_pages") var totalPages: Int = 0,
    @SerialName("total_results") var totalResults: Int = 0
)
