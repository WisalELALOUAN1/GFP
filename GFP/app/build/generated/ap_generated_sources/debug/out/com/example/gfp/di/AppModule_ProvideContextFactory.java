package com.example.gfp.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideContextFactory implements Factory<Context> {
  private final AppModule module;

  private final Provider<Context> ctxProvider;

  public AppModule_ProvideContextFactory(AppModule module, Provider<Context> ctxProvider) {
    this.module = module;
    this.ctxProvider = ctxProvider;
  }

  @Override
  public Context get() {
    return provideContext(module, ctxProvider.get());
  }

  public static AppModule_ProvideContextFactory create(AppModule module,
      Provider<Context> ctxProvider) {
    return new AppModule_ProvideContextFactory(module, ctxProvider);
  }

  public static Context provideContext(AppModule instance, Context ctx) {
    return Preconditions.checkNotNullFromProvides(instance.provideContext(ctx));
  }
}
