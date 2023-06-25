package com.kotlin.learn.catalog.core.network;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J/\u0010\t\u001a\u0002H\n\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u0001\"\u0006\b\u0001\u0010\n\u0018\u00012\u0006\u0010\f\u001a\u0002H\u000bH\u0080H\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000eJO\u0010\u000f\u001a\u0002H\n\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u0001\"\u0006\b\u0001\u0010\n\u0018\u00012\u0006\u0010\f\u001a\u0002H\u000b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0080H\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/kotlin/learn/catalog/core/network/KtorClient;", "", "chuckerInterceptor", "Lcom/chuckerteam/chucker/api/ChuckerInterceptor;", "(Lcom/chuckerteam/chucker/api/ChuckerInterceptor;)V", "client", "Lio/ktor/client/HttpClient;", "getClient$network_debug", "()Lio/ktor/client/HttpClient;", "sendRequestApi", "T", "Z", "resources", "sendRequestApi$network_debug", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendRequestApiWithQuery", "query", "", "", "path", "sendRequestApiWithQuery$network_debug", "(Ljava/lang/Object;Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "network_debug"})
public final class KtorClient {
    @org.jetbrains.annotations.NotNull
    private final io.ktor.client.HttpClient client = null;
    @org.jetbrains.annotations.NotNull
    public static final com.kotlin.learn.catalog.core.network.KtorClient.Companion Companion = null;
    
    public KtorClient(@org.jetbrains.annotations.NotNull
    com.chuckerteam.chucker.api.ChuckerInterceptor chuckerInterceptor) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.ktor.client.HttpClient getClient$network_debug() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u00a8\u0006\u0007"}, d2 = {"Lcom/kotlin/learn/catalog/core/network/KtorClient$Companion;", "", "()V", "initializeKtor", "Lio/ktor/client/HttpClient;", "chuckerInterceptor", "Lcom/chuckerteam/chucker/api/ChuckerInterceptor;", "network_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final io.ktor.client.HttpClient initializeKtor(com.chuckerteam.chucker.api.ChuckerInterceptor chuckerInterceptor) {
            return null;
        }
    }
}