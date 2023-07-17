package com.kotlin.learn.feature.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.learn.core.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {

    private val _splash: MutableStateFlow<Result<Int>> = MutableStateFlow(Result.Loading)
    val splash = _splash.asStateFlow()

}