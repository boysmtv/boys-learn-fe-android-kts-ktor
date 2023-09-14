package com.kotlin.learn.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
@JsonClass(generateAdapter = true)
data class RecentDataModel(
    @Json(name = "backdrop_path") var backdropPath: String? = null,
    @Json(name = "id") var id: Int? = null,
    @Json(name = "original_title") var originalTitle: String? = null,
    @Json(name = "imdb_id") var imdbId: String? = null,
    @Json(name = "poster_path") var posterPath: String? = null,
    @Json(name = "title") var title: String? = null,
)