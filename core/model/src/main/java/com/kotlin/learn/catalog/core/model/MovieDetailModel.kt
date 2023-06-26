package com.kotlin.learn.catalog.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailModel(
    @SerialName("adult") var adult: Boolean? = null,
    @SerialName("backdrop_path") var backdropPath: String? = null,
    @SerialName("belongs_to_collection") var belongsToCollection: BelongsToCollection? = BelongsToCollection(),
    @SerialName("budget") var budget: Int? = null,
    @SerialName("genres") var genres: ArrayList<Genres> = arrayListOf(),
    @SerialName("homepage") var homepage: String? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("imdb_id") var imdbId: String? = null,
    @SerialName("original_language") var originalLanguage: String? = null,
    @SerialName("original_title") var originalTitle: String? = null,
    @SerialName("overview") var overview: String? = null,
    @SerialName("popularity") var popularity: Double? = null,
    @SerialName("poster_path") var posterPath: String? = null,
    @SerialName("production_companies") var productionCompanies: ArrayList<ProductionCompanies> = arrayListOf(),
    @SerialName("production_countries") var productionCountries: ArrayList<ProductionCountries> = arrayListOf(),
    @SerialName("release_date") var releaseDate: String? = null,
    @SerialName("revenue") var revenue: Int? = null,
    @SerialName("runtime") var runtime: Int? = null,
    @SerialName("spoken_languages") var spokenLanguages: ArrayList<SpokenLanguages> = arrayListOf(),
    @SerialName("status") var status: String? = null,
    @SerialName("tagline") var tagline: String? = null,
    @SerialName("title") var title: String? = null,
    @SerialName("video") var video: Boolean? = null,
    @SerialName("vote_average") var voteAverage: Double? = null,
    @SerialName("vote_count") var voteCount: Int? = null
)

@Serializable
data class BelongsToCollection(
    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("poster_path") var posterPath: String? = null,
    @SerialName("backdrop_path") var backdropPath: String? = null

)

@Serializable
data class Genres(
    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null
)

@Serializable
data class ProductionCompanies(
    @SerialName("id") var id: Int? = null,
    @SerialName("logo_path") var logoPath: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("origin_country") var originCountry: String? = null
)

@Serializable
data class ProductionCountries(
    @SerialName("iso_3166_1") var iso31661: String? = null,
    @SerialName("name") var name: String? = null
)

@Serializable
data class SpokenLanguages(
    @SerialName("english_name") var englishName: String? = null,
    @SerialName("iso_639_1") var iso6391: String? = null,
    @SerialName("name") var name: String? = null
)