package com.kotlin.learn.catalog.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kotlin.learn.catalog.core.common.pagingSucceeded
import com.kotlin.learn.catalog.core.data.repository.MovieRepository
import com.kotlin.learn.catalog.core.model.NetworkMovieData
import com.kotlin.learn.catalog.core.utilities.Constant
import com.kotlin.learn.catalog.core.utilities.replaceIfNull

class MoviePagingSource(
    private val repository: MovieRepository,
) : PagingSource<Int, NetworkMovieData>() {

    override fun getRefreshKey(state: PagingState<Int, NetworkMovieData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(Constant.ONE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(Constant.ONE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkMovieData> {
        val page = params.key.replaceIfNull(Constant.ONE)
        return repository.getMovies(page).pagingSucceeded { data ->
            loadResult(data = data.results, page = page)
        }
    }

    private fun loadResult(data: List<NetworkMovieData>, page: Int) = LoadResult.Page(
        data = data,
        prevKey = if (page == Constant.ONE) null else page - Constant.ONE,
        nextKey = if (data.isEmpty()) null else page.plus(Constant.ONE)
    )

}