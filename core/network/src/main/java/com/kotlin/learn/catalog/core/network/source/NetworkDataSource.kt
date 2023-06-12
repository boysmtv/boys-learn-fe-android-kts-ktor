package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.model.NetworkMovie


interface NetworkDataSource {

    suspend fun getMovies(page: Int): NetworkMovie

}