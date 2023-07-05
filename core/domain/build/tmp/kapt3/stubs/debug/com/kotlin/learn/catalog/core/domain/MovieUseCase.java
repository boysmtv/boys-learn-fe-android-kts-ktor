package com.kotlin.learn.catalog.core.domain;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J&\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00040\u00032\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bH&J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u00032\u0006\u0010\u0013\u001a\u00020\u0014H&\u00a8\u0006\u0015"}, d2 = {"Lcom/kotlin/learn/catalog/core/domain/MovieUseCase;", "", "getBanner", "Lkotlinx/coroutines/flow/Flow;", "Lcom/kotlin/learn/catalog/core/common/Result;", "Lcom/kotlin/learn/catalog/core/model/MovieModel;", "page", "", "getDetailMovie", "Lcom/kotlin/learn/catalog/core/model/MovieDetailModel;", "movieId", "", "language", "getMovie", "Landroidx/paging/PagingData;", "Lcom/kotlin/learn/catalog/core/model/MovieDataModel;", "categories", "Lcom/kotlin/learn/catalog/core/utilities/MovieCategories;", "searchMovie", "searchModel", "Lcom/kotlin/learn/catalog/core/model/MovieSearchModel;", "domain_debug"})
public abstract interface MovieUseCase {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> getBanner(int page);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.kotlin.learn.catalog.core.model.MovieDataModel>> getMovie(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.utilities.MovieCategories categories);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieDetailModel>> getDetailMovie(@org.jetbrains.annotations.NotNull
    java.lang.String movieId, @org.jetbrains.annotations.NotNull
    java.lang.String language);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.kotlin.learn.catalog.core.model.MovieDataModel>> searchMovie(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.model.MovieSearchModel searchModel);
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}