package com.kotlin.learn.catalog.core.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J$\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0016J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/kotlin/learn/catalog/core/data/repository/MovieRepositoryImpl;", "Lcom/kotlin/learn/catalog/core/data/repository/MovieRepository;", "network", "Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSource;", "(Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSource;)V", "getCredits", "Lkotlinx/coroutines/flow/Flow;", "Lcom/kotlin/learn/catalog/core/common/Result;", "Lcom/kotlin/learn/catalog/core/model/CreditsModel;", "movieId", "", "getDetailMovie", "Lcom/kotlin/learn/catalog/core/model/MovieDetailModel;", "language", "getNowPlaying", "Lcom/kotlin/learn/catalog/core/model/MovieModel;", "page", "", "getPopular", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopRated", "getUpComing", "searchMovie", "searchModel", "Lcom/kotlin/learn/catalog/core/model/MovieSearchModel;", "(ILcom/kotlin/learn/catalog/core/model/MovieSearchModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class MovieRepositoryImpl implements com.kotlin.learn.catalog.core.data.repository.MovieRepository {
    @org.jetbrains.annotations.NotNull
    private final com.kotlin.learn.catalog.core.network.source.NetworkDataSource network = null;
    
    @javax.inject.Inject
    public MovieRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.network.source.NetworkDataSource network) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getPopular(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getTopRated(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getUpComing(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> getNowPlaying(int page) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieDetailModel>> getDetailMovie(@org.jetbrains.annotations.NotNull
    java.lang.String movieId, @org.jetbrains.annotations.NotNull
    java.lang.String language) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object searchMovie(int page, @org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.model.MovieSearchModel searchModel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.CreditsModel>> getCredits(@org.jetbrains.annotations.NotNull
    java.lang.String movieId) {
        return null;
    }
}