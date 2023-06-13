package com.kotlin.learn.catalog.core.data.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0006"}, d2 = {"Lcom/kotlin/learn/catalog/core/data/di/DataModule;", "", "bindMovieRepository", "Lcom/kotlin/learn/catalog/core/data/repository/MovieRepository;", "latestSmartphoneRepositoryImpl", "Lcom/kotlin/learn/catalog/core/data/repository/MovieRepositoryImpl;", "data_debug"})
@dagger.Module
public abstract interface DataModule {
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract com.kotlin.learn.catalog.core.data.repository.MovieRepository bindMovieRepository(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.data.repository.MovieRepositoryImpl latestSmartphoneRepositoryImpl);
}