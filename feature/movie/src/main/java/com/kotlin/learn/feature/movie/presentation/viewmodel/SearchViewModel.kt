package com.kotlin.learn.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlin.learn.core.domain.MovieUseCase
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.model.MovieSearchModel
import com.kotlin.learn.core.utilities.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _movieSearchModel : MutableStateFlow<MovieSearchModel> = MutableStateFlow(MovieSearchModel())

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchMovie : Flow<PagingData<MovieDataModel>> =
        _movieSearchModel.flatMapLatest { model ->
            if (model.title != Constant.EMPTY_STRING)
                movieUseCase.searchMovie(model)
            else emptyFlow()
        }.cachedIn(viewModelScope)

    fun searchMovie(query: String) {
        _movieSearchModel.value = MovieSearchModel(title = query)
    }

}