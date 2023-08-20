package com.kotlin.learn.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDetailModel(

    @SerialName("id")
    var id: Int? = null,

    @SerialName("results")
    var results: ArrayList<VideoResult> = arrayListOf()

)

@Serializable
data class VideoResult(
    @SerialName("iso_639_1") var iso6391: String? = null,
    @SerialName("iso_3166_1") var iso31661: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("key") var key: String? = null,
    @SerialName("site") var site: String? = null,
    @SerialName("size") var size: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("official") var official: Boolean? = null,
    @SerialName("published_at") var publishedAt: String? = null,
    @SerialName("id") var id: String? = null
)