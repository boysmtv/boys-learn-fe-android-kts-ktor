package com.kotlin.learn.catalog.core.model

import com.kotlin.learn.catalog.core.utilities.Constant

data class MovieSearchModel(
    val title: String = Constant.EMPTY_STRING
)