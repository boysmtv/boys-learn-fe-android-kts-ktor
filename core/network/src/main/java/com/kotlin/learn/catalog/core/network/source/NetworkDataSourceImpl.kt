package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.network.DiscoverMovie
import com.kotlin.learn.catalog.core.network.KtorClient
import com.kotlin.learn.catalog.core.model.NetworkMovie
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
                query = mapOf("page" to "$page")
            )
        }
    }

}