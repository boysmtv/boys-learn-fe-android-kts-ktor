package com.kotlin.learn.catalog.core.domain.di

import com.kotlin.learn.catalog.core.data.repository.MovieRepository
import com.kotlin.learn.catalog.core.data.repository.MovieRepositoryImpl
import com.kotlin.learn.catalog.core.domain.MovieUseCase
import com.kotlin.learn.catalog.core.domain.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindMovieUseCase(
        latestMovieUseCase: MovieUseCaseImpl,
    ): MovieUseCase

}