package com.kotlin.learn.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    @SerialName("page") var page: Int = 0,
    @SerialName("results") var results: List<MovieDataModel> = arrayListOf(),
    @SerialName("total_pages") var totalPages: Int = 0,
    @SerialName("total_results") var totalResults: Int = 0
)
