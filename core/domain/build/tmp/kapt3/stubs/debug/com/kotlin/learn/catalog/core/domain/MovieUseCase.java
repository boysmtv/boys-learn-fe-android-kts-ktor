package com.kotlin.learn.catalog.core.domain;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\u0006\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\r"}, d2 = {"Lcom/kotlin/learn/catalog/core/domain/MovieUseCase;", "", "getBanner", "Lkotlinx/coroutines/flow/Flow;", "Lcom/kotlin/learn/catalog/core/common/Result;", "Lcom/kotlin/learn/catalog/core/model/MovieModel;", "page", "", "getMovie", "Landroidx/paging/PagingData;", "Lcom/kotlin/learn/catalog/core/model/MovieDataModel;", "categories", "Lcom/kotlin/learn/catalog/core/utilities/MovieCategories;", "domain_debug"})
public abstract interface MovieUseCase {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.kotlin.learn.catalog.core.model.MovieDataModel>> getMovie(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.utilities.MovieCategories categories);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.kotlin.learn.catalog.core.common.Result<com.kotlin.learn.catalog.core.model.MovieModel>> getBanner(int page);
}