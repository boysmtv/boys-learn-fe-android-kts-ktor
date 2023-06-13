package com.kotlin.learn.catalog.core.data.repository;

import com.kotlin.learn.catalog.core.network.source.NetworkDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class MovieRepositoryImpl_Factory implements Factory<MovieRepositoryImpl> {
  private final Provider<NetworkDataSource> networkProvider;

  public MovieRepositoryImpl_Factory(Provider<NetworkDataSource> networkProvider) {
    this.networkProvider = networkProvider;
  }

  @Override
  public MovieRepositoryImpl get() {
    return newInstance(networkProvider.get());
  }

  public static MovieRepositoryImpl_Factory create(Provider<NetworkDataSource> networkProvider) {
    return new MovieRepositoryImpl_Factory(networkProvider);
  }

  public static MovieRepositoryImpl newInstance(NetworkDataSource network) {
    return new MovieRepositoryImpl(network);
  }
}
