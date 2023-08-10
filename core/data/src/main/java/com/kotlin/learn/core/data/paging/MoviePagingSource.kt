package com.kotlin.learn.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kotlin.learn.core.common.util.network.pagingSucceeded
import com.kotlin.learn.core.data.repository.MovieRepository
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.replaceIfNull

class MoviePagingSource(
    private val repository: MovieRepository,
) : PagingSource<Int, MovieDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieDataModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(Constant.ONE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(Constant.ONE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDataModel> {
        val page = params.key.replaceIfNull(Constant.ONE)
        return repository.getPopular(page).pagingSucceeded { data ->
            loadResult(data = data.results, page = page)
        }
    }

    private fun loadResult(data: List<MovieDataModel>, page: Int) = LoadResult.Page(
        data = data,
        prevKey = if (page == Constant.ONE) null else page - Constant.ONE,
        nextKey = if (data.isEmpty()) null else page.plus(Constant.ONE)
    )

}