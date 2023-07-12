package com.kotlin.learn.catalog.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.data.repository.MovieRepository
import com.kotlin.learn.catalog.core.domain.paging.MoviePagingSource
import com.kotlin.learn.catalog.core.domain.paging.SearchPagingSource
import com.kotlin.learn.catalog.core.model.CreditsModel
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.model.MovieDetailModel
import com.kotlin.learn.catalog.core.model.MovieModel
import com.kotlin.learn.catalog.core.model.MovieSearchModel
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : MovieUseCase {

    override fun getMovie(categories: MovieCategories): Flow<PagingData<MovieDataModel>> {
        return Pager(PagingConfig(pageSize = 10)) {
            MoviePagingSource(
                repository = movieRepository,
                categories = categories
            )
        }.flow
    }

    override fun getBanner(page: Int): Flow<Result<MovieModel>> {
        return movieRepository.getBanner(page = 1)
    }

    override fun getDetailMovie(movieId: String, language: String): Flow<Result<MovieDetailModel>> {
        return movieRepository.getDetailMovie(movieId = movieId, language = language)
    }

    override fun searchMovie(searchModel: MovieSearchModel): Flow<PagingData<MovieDataModel>> {
        return Pager(PagingConfig(pageSize = 10)) {
            SearchPagingSource(
                repository = movieRepository,
                searchModel = searchModel
            )
        }.flow
    }

    override fun getCredits(movieId: String): Flow<Result<CreditsModel>> {
        return movieRepository.getCredits(movieId = movieId)
    }

}
