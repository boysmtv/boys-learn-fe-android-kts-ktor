package com.kotlin.learn.core.network.source

import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.model.MovieModel
import com.kotlin.learn.core.model.MovieSearchModel
import com.kotlin.learn.core.model.VideoDetailModel
import com.kotlin.learn.core.network.CreditsMovie
import com.kotlin.learn.core.network.DetailMovie
import com.kotlin.learn.core.network.DetailVideos
import com.kotlin.learn.core.network.NowPlayingMovie
import com.kotlin.learn.core.network.PopularMovie
import com.kotlin.learn.core.network.SearchMovie
import com.kotlin.learn.core.network.TopRatedMovie
import com.kotlin.learn.core.network.UpComingMovie
import com.kotlin.learn.core.utilities.Constant.QUERY_PARAM_LANGUAGE
import com.kotlin.learn.core.utilities.Constant.QUERY_PARAM_PAGE
import com.kotlin.learn.core.utilities.Constant.QUERY_PARAM_QUERY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MovieDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
) : MovieDataSource {

    override suspend fun getPopular(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.getRequestApiWithQuery(
                resources = PopularMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString()
                )
            )
        }
    }

    override suspend fun getTopRated(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.getRequestApiWithQuery(
                resources = TopRatedMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString()
                )
            )
        }
    }

    override suspend fun getUpComing(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.getRequestApiWithQuery(
                resources = UpComingMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString()
                )
            )
        }
    }

    override suspend fun getNowPlaying(page: Int): MovieModel {
        return withContext(Dispatchers.IO) {
            ktorClient.getRequestApiWithQuery(
                resources = NowPlayingMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString()
                )
            )
        }
    }

    override suspend fun getDetailMovie(movieId: String, language: String): MovieDetailModel {
        return withContext(Dispatchers.IO){
            ktorClient.getRequestApiWithQuery(
                resources = DetailMovie(),
                query = mapOf(
                    QUERY_PARAM_LANGUAGE to language
                ),
                path = movieId
            )
        }
    }

    override suspend fun getDetailVideos(movieId: String, language: String): VideoDetailModel {
        return withContext(Dispatchers.IO){
            ktorClient.getRequestApiWithQuery(
                resources = DetailVideos(),
                query = mapOf(
                    QUERY_PARAM_LANGUAGE to language
                ),
                path = "$movieId/videos",
            )
        }
    }

    override suspend fun searchMovie(page: Int, searchModel: MovieSearchModel): MovieModel {
        return withContext(Dispatchers.IO){
            ktorClient.getRequestApiWithQuery(
                resources = SearchMovie(),
                query = mapOf(
                    QUERY_PARAM_PAGE to page.toString(),
                    QUERY_PARAM_QUERY to searchModel.title,
                )
            )
        }
    }

    override suspend fun getCredits(movieId: String): CreditsModel {
        return withContext(Dispatchers.IO){
            ktorClient.getRequestApiWithQuery(
                resources = CreditsMovie(),
                query = emptyMap(),
                path = "$movieId/credits"
            )
        }
    }

}