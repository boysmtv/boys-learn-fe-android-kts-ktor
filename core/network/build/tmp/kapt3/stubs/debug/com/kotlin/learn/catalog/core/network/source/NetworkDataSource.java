package com.kotlin.learn.catalog.core.network.source;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0007"}, d2 = {"Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSource;", "", "getMovies", "Lcom/kotlin/learn/catalog/core/model/NetworkMovie;", "page", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_debug"})
public abstract interface NetworkDataSource {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getMovies(int page, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kotlin.learn.catalog.core.model.NetworkMovie> continuation);
}