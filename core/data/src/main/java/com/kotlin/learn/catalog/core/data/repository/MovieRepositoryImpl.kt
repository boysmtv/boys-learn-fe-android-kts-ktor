package com.kotlin.learn.catalog.core.data.repository

import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.common.executeWithResponse
import com.kotlin.learn.catalog.core.model.CreditsModel
import com.kotlin.learn.catalog.core.model.MovieDetailModel
import com.kotlin.learn.catalog.core.model.MovieSearchModel
import com.kotlin.learn.catalog.core.network.source.MovieDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val network: MovieDataSource
) : MovieRepository {

    override suspend fun getPopular(page: Int) = withContext(Dispatchers.IO) {
        executeWithResponse {
            network.getPopular(page)
        }
    }

    override suspend fun getTopRated(page: Int) = withContext(Dispatchers.IO) {
        executeWithResponse {
            network.getTopRated(page)
        }
    }

    override suspend fun getUpComing(page: Int) = withContext(Dispatchers.IO) {
        executeWithResponse {
            network.getUpComing(page)
        }
    }

    override fun getBanner(page: Int) = flow {
        emit(executeWithResponse {
            network.getNowPlaying(page)
        })
    }.flowOn(Dispatchers.IO)

    override fun getDetailMovie(movieId: String, language: String): Flow<Result<MovieDetailModel>> = flow {
        emit(executeWithResponse {
            network.getDetailMovie(movieId = movieId, language = language)
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun searchMovie(page: Int, searchModel: MovieSearchModel) = withContext(Dispatchers.IO) {
        executeWithResponse {
            network.searchMovie(page, searchModel)
        }
    }

    override fun getCredits(movieId: String) : Flow<Result<CreditsModel>> = flow {
        emit(executeWithResponse {
            network.getCredits(movieId = movieId)
        })
    }.flowOn(Dispatchers.IO)

}