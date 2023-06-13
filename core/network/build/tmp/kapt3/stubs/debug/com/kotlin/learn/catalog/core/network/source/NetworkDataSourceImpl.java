package com.kotlin.learn.catalog.core.network.source;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSourceImpl;", "Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSource;", "ktorClient", "Lcom/kotlin/learn/catalog/core/network/KtorClient;", "(Lcom/kotlin/learn/catalog/core/network/KtorClient;)V", "getMovies", "Lcom/kotlin/learn/catalog/core/model/NetworkMovie;", "page", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_debug"})
public final class NetworkDataSourceImpl implements com.kotlin.learn.catalog.core.network.source.NetworkDataSource {
    private final com.kotlin.learn.catalog.core.network.KtorClient ktorClient = null;
    
    @javax.inject.Inject
    public NetworkDataSourceImpl(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.network.KtorClient ktorClient) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getMovies(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.NetworkMovie> continuation) {
        return null;
    }
}