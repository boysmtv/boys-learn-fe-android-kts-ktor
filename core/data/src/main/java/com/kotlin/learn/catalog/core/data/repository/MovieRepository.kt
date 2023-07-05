package com.kotlin.learn.catalog.core.data.repository

import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.model.MovieDetailModel
import com.kotlin.learn.catalog.core.model.MovieModel
import com.kotlin.learn.catalog.core.model.MovieSearchModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopular(page: Int): Result<MovieModel>

    suspend fun getTopRated(page: Int): Result<MovieModel>

    suspend fun getUpComing(page: Int): Result<MovieModel>

    fun getNowPlaying(page: Int): Flow<Result<MovieModel>>

    fun getDetailMovie(movieId: String, language: String): Flow<Result<MovieDetailModel>>

    suspend fun searchMovie(page: Int, searchModel: MovieSearchModel) : Result<MovieModel>
}