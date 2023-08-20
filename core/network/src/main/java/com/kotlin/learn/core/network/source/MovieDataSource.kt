package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.model.MovieModel
import com.kotlin.learn.core.model.MovieSearchModel
import com.kotlin.learn.core.model.VideoDetailModel

interface MovieDataSource {

    suspend fun getPopular(page: Int): MovieModel

    suspend fun getTopRated(page: Int): MovieModel

    suspend fun getUpComing(page: Int): MovieModel

    suspend fun getNowPlaying(page: Int): MovieModel

    suspend fun getDetailMovie(movieId: String, language: String): MovieDetailModel

    suspend fun getDetailVideos(movieId: String, language: String): VideoDetailModel

    suspend fun searchMovie(page: Int, searchModel: MovieSearchModel): MovieModel

    suspend fun getCredits(movieId: String): CreditsModel

}