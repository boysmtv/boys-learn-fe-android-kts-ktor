package com.kotlin.learn.catalog.core.network.source;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ!\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J!\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSourceImpl;", "Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSource;", "ktorClient", "Lcom/kotlin/learn/catalog/core/network/KtorClient;", "(Lcom/kotlin/learn/catalog/core/network/KtorClient;)V", "getCredits", "Lcom/kotlin/learn/catalog/core/model/CreditsModel;", "movieId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDetailMovie", "Lcom/kotlin/learn/catalog/core/model/MovieDetailModel;", "language", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNowPlaying", "Lcom/kotlin/learn/catalog/core/model/MovieModel;", "page", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopular", "getTopRated", "getUpComing", "searchMovie", "searchModel", "Lcom/kotlin/learn/catalog/core/model/MovieSearchModel;", "(ILcom/kotlin/learn/catalog/core/model/MovieSearchModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_debug"})
public final class NetworkDataSourceImpl implements com.kotlin.learn.catalog.core.network.source.NetworkDataSource {
    @org.jetbrains.annotations.NotNull
    private final com.kotlin.learn.catalog.core.network.KtorClient ktorClient = null;
    
    @javax.inject.Inject
    public NetworkDataSourceImpl(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.network.KtorClient ktorClient) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getPopular(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getTopRated(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getUpComing(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getNowPlaying(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getDetailMovie(@org.jetbrains.annotations.NotNull
    java.lang.String movieId, @org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieDetailModel> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object searchMovie(int page, @org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.model.MovieSearchModel searchModel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.MovieModel> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getCredits(@org.jetbrains.annotations.NotNull
    java.lang.String movieId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.CreditsModel> $completion) {
        return null;
    }
}