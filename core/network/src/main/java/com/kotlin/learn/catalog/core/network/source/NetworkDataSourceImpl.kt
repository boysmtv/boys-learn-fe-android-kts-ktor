package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.network.DiscoverMovie
import com.kotlin.learn.catalog.core.network.KtorClient
import com.kotlin.learn.catalog.core.model.NetworkMovie
import com.kotlin.learn.catalog.core.network.PopularMovie
import com.kotlin.learn.catalog.core.network.TopRatedMovie
import com.kotlin.learn.catalog.core.network.UpComingMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val ktorClient: KtorClient,
) : NetworkDataSource {

    override suspend fun getMovies(page: Int): NetworkMovie {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = DiscoverMovie(),
                query = mapOf(
                    "page" to "$page"
                )
            )
        }
    }

    override suspend fun getPopular(page: Int): NetworkMovie {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = PopularMovie(),
                query = mapOf(
                    "page" to "$page"
                )
            )
        }
    }

    override suspend fun getTopRated(page: Int): NetworkMovie {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = TopRatedMovie(),
                query = mapOf(
                    "page" to "$page"
                )
            )
        }
    }

    override suspend fun getUpComing(page: Int): NetworkMovie {
        return withContext(Dispatchers.IO) {
            ktorClient.sendRequestApiWithQuery(
                resources = UpComingMovie(),
                query = mapOf(
                    "page" to "$page"
                )
            )
        }
    }

}