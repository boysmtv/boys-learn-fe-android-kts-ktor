package com.kotlin.learn.catalog.core.domain.paging;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ#\u0010\t\u001a\u0004\u0018\u00010\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u000bH\u0016\u00a2\u0006\u0002\u0010\fJ+\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J$\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/kotlin/learn/catalog/core/domain/paging/MoviePagingSource;", "Landroidx/paging/PagingSource;", "", "Lcom/kotlin/learn/catalog/core/model/MovieDataModel;", "repository", "Lcom/kotlin/learn/catalog/core/data/repository/MovieRepository;", "categories", "Lcom/kotlin/learn/catalog/core/utilities/MovieCategories;", "(Lcom/kotlin/learn/catalog/core/data/repository/MovieRepository;Lcom/kotlin/learn/catalog/core/utilities/MovieCategories;)V", "getRefreshKey", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/PagingState;)Ljava/lang/Integer;", "load", "Landroidx/paging/PagingSource$LoadResult;", "params", "Landroidx/paging/PagingSource$LoadParams;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadResult", "Landroidx/paging/PagingSource$LoadResult$Page;", "response", "Lcom/kotlin/learn/catalog/core/model/MovieModel;", "page", "domain_debug"})
public final class MoviePagingSource extends androidx.paging.PagingSource<java.lang.Integer, com.kotlin.learn.catalog.core.model.MovieDataModel> {
    @org.jetbrains.annotations.NotNull
    private final com.kotlin.learn.catalog.core.data.repository.MovieRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final com.kotlin.learn.catalog.core.utilities.MovieCategories categories = null;
    
    public MoviePagingSource(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.data.repository.MovieRepository repository, @org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.utilities.MovieCategories categories) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Integer getRefreshKey(@org.jetbrains.annotations.NotNull
    androidx.paging.PagingState<java.lang.Integer, com.kotlin.learn.catalog.core.model.MovieDataModel> state) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object load(@org.jetbrains.annotations.NotNull
    androidx.paging.PagingSource.LoadParams<java.lang.Integer> params, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<java.lang.Integer, com.kotlin.learn.catalog.core.model.MovieDataModel>> $completion) {
        return null;
    }
    
    private final androidx.paging.PagingSource.LoadResult.Page<java.lang.Integer, com.kotlin.learn.catalog.core.model.MovieDataModel> loadResult(com.kotlin.learn.catalog.core.model.MovieModel response, int page) {
        return null;
    }
}