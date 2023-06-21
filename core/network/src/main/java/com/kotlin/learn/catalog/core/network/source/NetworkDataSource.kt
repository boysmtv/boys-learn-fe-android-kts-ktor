package com.kotlin.learn.catalog.core.network.source

import com.kotlin.learn.catalog.core.model.MovieModel


interface NetworkDataSource {

    suspend fun getPopular(page: Int): MovieModel

    suspend fun getTopRated(page: Int): MovieModel

    suspend fun getUpComing(page: Int): MovieModel

    suspend fun getNowPlaying(page: Int): MovieModel

}