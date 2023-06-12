package com.kotlin.learn.catalog.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kotlin.learn.catalog.core.data.repository.MovieRepository
import com.kotlin.learn.catalog.core.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.data.paging.MoviePagingSource
import com.kotlin.learn.catalog.core.model.NetworkMovieData

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<PagingData<NetworkMovieData>> {
        return Pager(PagingConfig(pageSize = 10)) {
            MoviePagingSource(
                repository = movieRepository,
            )
        }.flow
    }
}