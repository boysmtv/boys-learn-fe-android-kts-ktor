package com.kotlin.learn.catalog.core.network.di;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.kotlin.learn.catalog.core.network.KtorClient;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class KtorModule_ProvideKtorClientFactory implements Factory<KtorClient> {
  private final KtorModule module;

  private final Provider<ChuckerInterceptor> chuckerInterceptorProvider;

  public KtorModule_ProvideKtorClientFactory(KtorModule module,
      Provider<ChuckerInterceptor> chuckerInterceptorProvider) {
    this.module = module;
    this.chuckerInterceptorProvider = chuckerInterceptorProvider;
  }

  @Override
  public KtorClient get() {
    return provideKtorClient(module, chuckerInterceptorProvider.get());
  }

  public static KtorModule_ProvideKtorClientFactory create(KtorModule module,
      Provider<ChuckerInterceptor> chuckerInterceptorProvider) {
    return new KtorModule_ProvideKtorClientFactory(module, chuckerInterceptorProvider);
  }

  public static KtorClient provideKtorClient(KtorModule instance,
      ChuckerInterceptor chuckerInterceptor) {
    return Preconditions.checkNotNullFromProvides(instance.provideKtorClient(chuckerInterceptor));
  }
}
