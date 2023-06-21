package com.kotlin.learn.catalog.core.utilities.extension

import android.annotation.SuppressLint
import android.net.Uri
import com.kotlin.learn.catalog.core.utilities.Constant
import java.text.NumberFormat
import java.util.*

/**
 * Double
 */
fun Double?.replaceIfNull(replacementValue: Double = Constant.ZERO_DOUBLE): Double {
    if (this == null)
        return replacementValue
    return this
}

fun Double?.toNumberFormat(): String {
    val format = NumberFormat.getNumberInstance(Locale.US)
    return format.format(this.replaceIfNull())
}

fun Double?.toCurrencyFormat(locale: Locale = Locale.US): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(locale)
    format.maximumFractionDigits = Constant.TWO

    return format.format(this.replaceIfNull())
}

/**
 * Int
 */
fun Int?.replaceIfNull(replacementValue: Int = Constant.ZERO): Int {
    if (this == null)
        return replacementValue
    return this
}

fun Int?.toAnimeScoreFormat(): String {
    return when (this.replaceIfNull()) {
        Constant.ONE -> "(1) - Appaling"
        Constant.TWO -> "(2) - Horrible"
        Constant.THREE -> "(3) - Very Bad"
        Constant.FOUR -> "(4) - Bad"
        Constant.FIVE -> "(5) - Average"
        Constant.SIX -> "(6) - Fine"
        Constant.SEVEN -> "(7) - Good"
        Constant.EIGHT -> "(8) - Very Good"
        Constant.NINE -> "(9) - Great"
        Constant.TEN -> "(10) - Masterpiece"
        else -> Constant.EMPTY_STRING
    }
}

/**
 * List
 */
fun <T> List<T>?.replaceIfNull(replacementValue: List<T> = emptyList()): List<T> {
    if (this == null) {
        return replacementValue
    }
    return this
}

/**
 * String
 */
fun String?.replaceIfNull(replacementValue: String = Constant.EMPTY_STRING): String {
    if (this == null)
        return replacementValue
    return this
}

@SuppressLint("DefaultLocale")
fun String.capitalizeWords(): String =
    split(Constant.BLANK_SPACE)
        .joinToString(Constant.BLANK_SPACE) { text ->
            text.lowercase(Locale.getDefault())
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }


@SuppressLint("DefaultLocale")
fun String.lowerCaseWords(): String =
    split(Constant.BLANK_SPACE)
        .joinToString(Constant.BLANK_SPACE) {
            it.lowercase(Locale.getDefault())
        }

fun String?.animeRatingFormat(): String {
    return when (this.replaceIfNull()) {
        "g" -> "All Ages"
        "pg" -> "Children"
        "pg_13" -> "Teens 13 or older"
        "r" -> "17+ (violence & profanity)"
        "r+" -> "Mild Nudity"
        "rx" -> "Hentai"
        else -> ""
    }
}

fun String?.animeSourceFormat(): String {
    return when (this.replaceIfNull()) {
        "tv" -> "TV"
        "ova" -> "OVA"
        "movie" -> "Movie"
        "special" -> "Special"
        "ona" -> "ONA"
        "music" -> "Music"
        "manga" -> "Manga"
        "one_shot" -> "One-Shot"
        "doujinshi" -> "Doujinshi"
        "light_novel" -> "Light Novel"
        "novel" -> "Novel"
        "manhwa" -> "Manhwa"
        "manhua" -> "Manhua"
        else -> ""
    }
}

fun String?.getSubReddit(): String {
    val uri = Uri.parse(this)
    val segment = uri.path?.split(Constant.FORWARD_SLASH)
    return String.format(
        Constant.STRING_FORMAT_REDDIT,
        segment?.get(segment.size - Constant.TWO)
    )
}

fun String?.myAnimeListStatusFormatted(ifBlank: String = Constant.EMPTY_STRING): String {
    return this
        .replaceIfNull()
        .replace(Constant.UNDERSCORE, Constant.BLANK_SPACE)
        .capitalizeWords()
        .ifBlank { ifBlank }
}


fun String?.checkContain(contain: String): Boolean {
    return this.replaceIfNull()
        .contains(contain, true)
}

fun String.myAnimeListStatusApiFormat(): String {
    return this
        .lowerCaseWords()
        .replace(Constant.STRIP, Constant.BLANK_SPACE)
        .replace(Constant.BLANK_SPACE, Constant.UNDERSCORE)
}