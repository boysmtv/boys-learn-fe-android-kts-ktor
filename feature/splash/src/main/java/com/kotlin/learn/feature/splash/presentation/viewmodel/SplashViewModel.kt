package com.kotlin.learn.feature.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.data.preferences.DataStorePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil
) : ViewModel() {


}