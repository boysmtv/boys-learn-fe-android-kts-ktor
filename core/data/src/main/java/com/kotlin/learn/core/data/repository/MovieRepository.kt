package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.model.MovieModel
import com.kotlin.learn.core.model.MovieSearchModel
import com.kotlin.learn.core.model.VideoDetailModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopular(page: Int): Result<MovieModel>

    suspend fun getTopRated(page: Int): Result<MovieModel>

    suspend fun getUpComing(page: Int): Result<MovieModel>

    fun getBanner(page: Int): Flow<Result<MovieModel>>

    fun getDetailMovie(movieId: String, language: String): Flow<Result<MovieDetailModel>>

    fun getDetailVideos(movieId: String, language: String): Flow<Result<VideoDetailModel>>

    suspend fun searchMovie(page: Int, searchModel: MovieSearchModel) : Result<MovieModel>

    fun getCredits(movieId: String) : Flow<Result<CreditsModel>>
}