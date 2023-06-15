package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.model.NetworkMovie


interface NetworkDataSource {

    suspend fun getMovies(page: Int): NetworkMovie

    suspend fun getPopular(page: Int): NetworkMovie

    suspend fun getTopRated(page: Int): NetworkMovie

    suspend fun getUpComing(page: Int): NetworkMovie

}