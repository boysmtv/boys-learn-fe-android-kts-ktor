package com.kotlin.learn.feature.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.util.DataStoreCacheEvent
import com.kotlin.learn.core.data.repository.PreferencesRepository
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    fun fetchDataAuth() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    preferencesRepository.getString(PreferenceConstants.Authorization.PREF_GOOGLE_AUTH).getOrNull()
                        .orEmpty()
                )
            )
        }

}