package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.execute
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.model.MovieSearchModel
import com.kotlin.learn.core.network.source.MovieDataSource
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
        execute {
            network.getPopular(page)
        }
    }

    override suspend fun getTopRated(page: Int) = withContext(Dispatchers.IO) {
        execute {
            network.getTopRated(page)
        }
    }

    override suspend fun getUpComing(page: Int) = withContext(Dispatchers.IO) {
        execute {
            network.getUpComing(page)
        }
    }

    override fun getBanner(page: Int) = flow {
        emit(
            execute {
                network.getNowPlaying(page)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun getDetailMovie(movieId: String, language: String): Flow<Result<MovieDetailModel>> = flow {
        emit(
            execute {
                network.getDetailMovie(movieId = movieId, language = language)
            }
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun searchMovie(page: Int, searchModel: MovieSearchModel) = withContext(Dispatchers.IO) {
        execute {
            network.searchMovie(page, searchModel)
        }
    }

    override fun getCredits(movieId: String): Flow<Result<CreditsModel>> = flow {
        emit(
            execute {
                network.getCredits(movieId = movieId)
            }
        )
    }.flowOn(Dispatchers.IO)

}