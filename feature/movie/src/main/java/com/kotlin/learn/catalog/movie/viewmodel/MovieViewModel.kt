package com.kotlin.learn.catalog.movie.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.learn.catalog.core.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    movieUseCase : MovieUseCase
) : ViewModel() {



}