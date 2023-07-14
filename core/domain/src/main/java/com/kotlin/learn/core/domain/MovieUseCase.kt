package com.kotlin.learn.core.domain

import androidx.paging.PagingData
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.model.MovieModel
import com.kotlin.learn.core.model.MovieSearchModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.MovieCategories
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getBanner(page: Int): Flow<Result<MovieModel>>

    fun getMovie(categories: MovieCategories): Flow<PagingData<MovieDataModel>>

    fun getDetailMovie(movieId: String, language: String = Constant.MOVIE_LANGUAGE): Flow<Result<MovieDetailModel>>

    fun searchMovie(searchModel: MovieSearchModel): Flow<PagingData<MovieDataModel>>

    fun getCredits(movieId: String): Flow<Result<CreditsModel>>

}