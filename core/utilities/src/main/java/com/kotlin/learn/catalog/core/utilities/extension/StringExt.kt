package com.kotlin.learn.catalog.core.utilities.extension

import java.util.Locale

fun String.capitalize(): String {
    return this.trim().split("\\s+".toRegex())
        .joinToString(" ") { first -> first.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } }
}