package com.kotlin.learn.core.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.data.repository.MovieRepository
import com.kotlin.learn.core.domain.paging.MoviePagingSource
import com.kotlin.learn.core.domain.paging.SearchPagingSource
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.model.MovieModel
import com.kotlin.learn.core.model.MovieSearchModel
import com.kotlin.learn.core.model.VideoDetailModel
import com.kotlin.learn.core.utilities.MovieCategories
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

    override fun getDetailVideo(movieId: String, language: String): Flow<Result<VideoDetailModel>> {
        return movieRepository.getDetailVideos(movieId = movieId, language = language)
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
