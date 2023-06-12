package com.kotlin.learn.catalog.core.data.repository

import com.kotlin.learn.catalog.core.common.executeWithResponse
import com.kotlin.learn.catalog.core.network.source.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val network: NetworkDataSource,
) : MovieRepository {

    override suspend fun getMovies(page: Int) = withContext(Dispatchers.IO) {
        executeWithResponse {
            network.getMovies(page)
        }
    }

}