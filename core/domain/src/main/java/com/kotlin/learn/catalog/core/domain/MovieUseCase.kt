package com.kotlin.learn.catalog.core.domain

import androidx.paging.PagingData
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.model.MovieDetailModel
import com.kotlin.learn.catalog.core.model.MovieModel
import com.kotlin.learn.catalog.core.utilities.Constant
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getBanner(page: Int): Flow<Result<MovieModel>>

    fun getMovie(categories: MovieCategories): Flow<PagingData<MovieDataModel>>

    fun getDetailMovie(movieId: String, language: String = Constant.MOVIE_LANGUAGE): Flow<Result<MovieDetailModel>>

}