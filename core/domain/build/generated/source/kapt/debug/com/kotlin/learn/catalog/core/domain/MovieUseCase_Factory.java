package com.kotlin.learn.catalog.core.domain;

import com.kotlin.learn.catalog.core.data.repository.MovieRepository;
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
public final class MovieUseCase_Factory implements Factory<MovieUseCase> {
  private final Provider<MovieRepository> movieRepositoryProvider;

  public MovieUseCase_Factory(Provider<MovieRepository> movieRepositoryProvider) {
    this.movieRepositoryProvider = movieRepositoryProvider;
  }

  @Override
  public MovieUseCase get() {
    return newInstance(movieRepositoryProvider.get());
  }

  public static MovieUseCase_Factory create(Provider<MovieRepository> movieRepositoryProvider) {
    return new MovieUseCase_Factory(movieRepositoryProvider);
  }

  public static MovieUseCase newInstance(MovieRepository movieRepository) {
    return new MovieUseCase(movieRepository);
  }
}
