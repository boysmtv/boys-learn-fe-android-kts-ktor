package com.kotlin.learn.catalog.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.data.repository.MovieRepository
import com.kotlin.learn.catalog.core.domain.paging.MoviePagingSource
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.model.MovieModel
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
        return movieRepository.getNowPlaying(page = 1)
    }

}
