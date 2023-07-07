package com.kotlin.learn.catalog.core.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J$\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H&J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\'\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/kotlin/learn/catalog/core/data/repository/MovieRepository;", "", "getCredits", "Lkotlinx/coroutines/flow/Flow;", "Lcom/kotlin/learn/catalog/core/common/Result;", "Lcom/kotlin/learn/catalog/core/model/CreditsModel;", "movieId", "", "getDetailMovie", "Lcom/kotlin/learn/catalog/core/model/MovieDetailModel;", "language", "getNowPlaying", "Lcom/kotlin/learn/catalog/core/model/MovieModel;", "page", "", "getPopular", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopRated", "getUpComing", "searchMovie", "searchModel", "Lcom/kotlin/learn/catalog/core/model/MovieSearchModel;", "(ILcom/kotlin/learn/catalog/core/model/MovieSearchModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface MovieRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPopular(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTopRated(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUpComing(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> getNowPlaying(int page);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieDetailModel>> getDetailMovie(@org.jetbrains.annotations.NotNull
    java.lang.String movieId, @org.jetbrains.annotations.NotNull
    java.lang.String language);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchMovie(int page, @org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.model.MovieSearchModel searchModel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.CreditsModel>> getCredits(@org.jetbrains.annotations.NotNull
    java.lang.String movieId);
}