package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.network.KtorClient
import com.kotlin.learn.catalog.core.model.MovieModel
import com.kotlin.learn.catalog.core.network.NowPlayingMovie
import com.kotlin.learn.catalog.core.network.PopularMovie
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

}