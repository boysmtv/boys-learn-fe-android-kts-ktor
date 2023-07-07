package com.kotlin.learn.catalog.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.learn.catalog.core.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CrewViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {



}