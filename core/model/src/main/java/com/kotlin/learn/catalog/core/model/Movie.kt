package com.kotlin.learn.catalog.core.model

import com.kotlin.learn.catalog.core.utilities.Constant

data class Movie(
    val brandId: Int = Constant.ZERO,
    val brandName: String = Constant.EMPTY_STRING,
    val brandSlug: String = Constant.EMPTY_STRING,
    val deviceCount: Int = Constant.ZERO,
    val detail: String = Constant.EMPTY_STRING
)