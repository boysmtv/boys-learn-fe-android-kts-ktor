package com.kotlin.learn.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsModel(
    @SerialName("id") var id: Int? = null,
    @SerialName("cast") var cast: ArrayList<CastModel> = arrayListOf(),
    @SerialName("crew") var crew: ArrayList<CrewModel> = arrayListOf()
)

@Serializable
data class CastModel(
    @SerialName("adult") var adult: Boolean? = null,
    @SerialName("gender") var gender: Int? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("known_for_department") var knownForDepartment: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("original_name") var originalName: String? = null,
    @SerialName("popularity") var popularity: Double? = null,
    @SerialName("profile_path") var profilePath: String? = null,
    @SerialName("cast_id") var castId: Int? = null,
    @SerialName("character") var character: String? = null,
    @SerialName("credit_id") var creditId: String? = null,
    @SerialName("order") var order: Int? = null
)

@Serializable
data class CrewModel(
    @SerialName("adult") var adult: Boolean? = null,
    @SerialName("gender") var gender: Int? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("known_for_department") var knownForDepartment: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("original_name") var originalName: String? = null,
    @SerialName("popularity") var popularity: Double? = null,
    @SerialName("profile_path") var profilePath: String? = null,
    @SerialName("credit_id") var creditId: String? = null,
    @SerialName("department") var department: String? = null,
    @SerialName("job") var job: String? = null
)