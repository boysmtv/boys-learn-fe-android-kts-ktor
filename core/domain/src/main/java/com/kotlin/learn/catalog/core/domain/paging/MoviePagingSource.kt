package com.kotlin.learn.catalog.core.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kotlin.learn.catalog.core.common.pagingSucceeded
import com.kotlin.learn.catalog.core.data.repository.MovieRepository
import com.kotlin.learn.catalog.core.model.NetworkMovie
import com.kotlin.learn.catalog.core.model.NetworkMovieData
import com.kotlin.learn.catalog.core.utilities.Constant
import com.kotlin.learn.catalog.core.utilities.replaceIfNull

class MoviePagingSource(
    private val repository: MovieRepository,
) : PagingSource<Int, NetworkMovieData>() {

    override fun getRefreshKey(state: PagingState<Int, NetworkMovieData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.minus(Constant.ONE)
                ?: state.closestPageToPosition(it)?.nextKey?.plus(Constant.ONE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkMovieData> {
        val page = params.key.replaceIfNull(1)
        return repository.getMovies(page)
            .pagingSucceeded { response -> loadResult(response = response, page = page) }
    }

    private fun loadResult(response: NetworkMovie, page: Int) =
        LoadResult.Page(
            data = response.results,
            prevKey = if (page != Constant.ONE) page.minus(Constant.ONE) else null,
            nextKey = if (page != response.totalPages) page.plus(Constant.ONE) else null
        )
}