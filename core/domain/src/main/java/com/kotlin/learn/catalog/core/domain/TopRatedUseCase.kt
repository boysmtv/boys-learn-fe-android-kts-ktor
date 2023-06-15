package com.kotlin.learn.catalog.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kotlin.learn.catalog.core.data.repository.MovieRepository
import com.kotlin.learn.catalog.core.domain.paging.MoviePagingSource
import com.kotlin.learn.catalog.core.model.NetworkMovieData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedUseCase @Inject constructor(
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
