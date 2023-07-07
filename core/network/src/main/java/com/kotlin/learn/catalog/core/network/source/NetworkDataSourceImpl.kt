package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.model.CreditsModel
import com.kotlin.learn.catalog.core.model.MovieDetailModel
import com.kotlin.learn.catalog.core.network.KtorClient
import com.kotlin.learn.catalog.core.model.MovieModel
import com.kotlin.learn.catalog.core.model.MovieSearchModel
import com.kotlin.learn.catalog.core.network.CreditsMovie
import com.kotlin.learn.catalog.core.network.DetailMovie
import com.kotlin.learn.catalog.core.network.NowPlayingMovie
import com.kotlin.learn.catalog.core.network.PopularMovie
import com.kotlin.learn.catalog.core.network.SearchMovie
import com.kotlin.learn.catalog.core.network.TopRatedMovie
import com.kotlin.learn.catalog.core.network.UpComingMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NetworkDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
) : NetworkDataSource {

    override suspend fun getPopular(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = PopularMovie(),
                query = mapOf(
                    "page" to "$page"
                )
            )
        }
    }


    override suspend fun getTopRated(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = TopRatedMovie(),
                query = mapOf(
                    "page" to "$page"
                )
            )
        }
    }

    override suspend fun getUpComing(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = UpComingMovie(),
                query = mapOf(
                    "page" to "$page"
                )
            )
        }
    }

    override suspend fun getNowPlaying(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = NowPlayingMovie(),
                query = mapOf(
                    "page" to "$page"
                )
            )
        }
    }

    override suspend fun getDetailMovie(movieId: String, language: String): MovieDetailModel {
        return withContext(Dispatchers.IO){
            ktorClient.sendRequestApiWithQuery(
                resources = DetailMovie(),
                query = mapOf(
                    "language" to language
                ),
                path = movieId
            )
        }
    }

    override suspend fun searchMovie(page: Int, searchModel: MovieSearchModel): MovieModel {
        return withContext(Dispatchers.IO){
            ktorClient.sendRequestApiWithQuery(
                resources = SearchMovie(),
                query = mapOf(
                    "page" to "$page",
                    "query" to searchModel.title,
                )
            )
        }
    }

    override suspend fun getCredits(movieId: String): CreditsModel {
        return withContext(Dispatchers.IO){
            ktorClient.sendRequestApiWithQuery(
                resources = CreditsMovie(),
                query = emptyMap(),
                path = "$movieId/credits"
            )
        }
    }

}