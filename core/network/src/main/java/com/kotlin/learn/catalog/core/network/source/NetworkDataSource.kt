package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.model.CreditsModel
import com.kotlin.learn.catalog.core.model.MovieDetailModel
import com.kotlin.learn.catalog.core.model.MovieModel
import com.kotlin.learn.catalog.core.model.MovieSearchModel


interface NetworkDataSource {

    suspend fun getPopular(page: Int): MovieModel

    suspend fun getTopRated(page: Int): MovieModel

    suspend fun getUpComing(page: Int): MovieModel

    suspend fun getNowPlaying(page: Int): MovieModel

    suspend fun getDetailMovie(movieId: String, language: String): MovieDetailModel

    suspend fun searchMovie(page: Int, searchModel: MovieSearchModel): MovieModel

    suspend fun getCredits(movieId: String): CreditsModel

}