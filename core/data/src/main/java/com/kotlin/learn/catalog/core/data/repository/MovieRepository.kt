package com.kotlin.learn.catalog.core.data.repository

import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.model.NetworkMovie

interface MovieRepository {

    suspend fun getMovies(page: Int): Result<NetworkMovie>

}