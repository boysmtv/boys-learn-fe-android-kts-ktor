package com.kotlin.learn.catalog.core.network.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\'\u00a8\u0006\u0005"}, d2 = {"Lcom/kotlin/learn/catalog/core/network/di/DataSourceModule;", "", "binds", "Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSource;", "Lcom/kotlin/learn/catalog/core/network/source/NetworkDataSourceImpl;", "network_debug"})
@dagger.Module
public abstract interface DataSourceModule {
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract com.kotlin.learn.catalog.core.network.source.NetworkDataSource binds(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.network.source.NetworkDataSourceImpl $this$binds);
}