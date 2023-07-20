package com.kotlin.learn.feature.auth.util.common

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserInfo(
    var name: String? = "",
    var mobile: String? = ""
)