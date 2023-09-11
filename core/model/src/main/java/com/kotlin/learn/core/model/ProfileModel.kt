package com.kotlin.learn.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
@JsonClass(generateAdapter = true)
data class ProfileModel(
    @Json(name = "id") var id: String? = null,
    @Json(name = "userId") var userId: String? = null,
    @Json(name = "connection") var connection: ConnectionModel? = null,
    @Json(name = "permission") var permission: PermissionModel? = null,
    @Json(name = "setting") var setting: SettingModel? = null,
    @Json(name = "createdAt") var createdAt: String? = null,
    @Json(name = "updatedAt") var updatedAt: String? = null,
)

@Serializable
@JsonClass(generateAdapter = true)
data class PermissionModel(
    @Json(name = "location") var location: Boolean = false,
    @Json(name = "internet") var internet: Boolean = false,
    @Json(name = "notification") var notification: Boolean = false,
)

@Serializable
@JsonClass(generateAdapter = true)
data class ConnectionModel(
    @Json(name = "mobile") var mobile: Boolean = false,
    @Json(name = "wifi") var wifi: Boolean = false,
    @Json(name = "bluetooth") var bluetooth: Boolean = false,
    @Json(name = "ethernet") var ethernet: Boolean = false,
)

@Serializable
@JsonClass(generateAdapter = true)
data class SettingModel(
    @Json(name = "login") var login: Boolean = false,
    @Json(name = "favourite") var favourite: Boolean = false,
    @Json(name = "notification") var notification: Boolean = false,
)
