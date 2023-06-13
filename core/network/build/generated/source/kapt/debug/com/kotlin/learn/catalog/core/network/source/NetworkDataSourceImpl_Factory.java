package com.kotlin.learn.catalog.core.network.source;

import com.kotlin.learn.catalog.core.network.KtorClient;
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
public final class NetworkDataSourceImpl_Factory implements Factory<NetworkDataSourceImpl> {
  private final Provider<KtorClient> ktorClientProvider;

  public NetworkDataSourceImpl_Factory(Provider<KtorClient> ktorClientProvider) {
    this.ktorClientProvider = ktorClientProvider;
  }

  @Override
  public NetworkDataSourceImpl get() {
    return newInstance(ktorClientProvider.get());
  }

  public static NetworkDataSourceImpl_Factory create(Provider<KtorClient> ktorClientProvider) {
    return new NetworkDataSourceImpl_Factory(ktorClientProvider);
  }

  public static NetworkDataSourceImpl newInstance(KtorClient ktorClient) {
    return new NetworkDataSourceImpl(ktorClient);
  }
}
