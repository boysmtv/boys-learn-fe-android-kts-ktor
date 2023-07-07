package com.kotlin.learn.catalog.core.network.source;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSource;", "", "getCredits", "Lcom/kotlin/learn/catalog/core/model/CreditsModel;", "movieId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDetailMovie", "Lcom/kotlin/learn/catalog/core/model/MovieDetailModel;", "language", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNowPlaying", "Lcom/kotlin/learn/catalog/core/model/MovieModel;", "page", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopular", "getTopRated", "getUpComing", "searchMovie", "searchModel", "Lcom/kotlin/learn/catalog/core/model/MovieSearchModel;", "(ILcom/kotlin/learn/catalog/core/model/MovieSearchModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_debug"})
public abstract interface NetworkDataSource {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPopular(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTopRated(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUpComing(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getNowPlaying(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDetailMovie(@org.jetbrains.annotations.NotNull
    java.lang.String movieId, @org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieDetailModel> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchMovie(int page, @org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.model.MovieSearchModel searchModel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCredits(@org.jetbrains.annotations.NotNull
    java.lang.String movieId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.CreditsModel> $completion);
}