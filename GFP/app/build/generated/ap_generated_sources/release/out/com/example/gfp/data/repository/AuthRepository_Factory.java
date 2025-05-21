package com.example.gfp.data.repository;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
    "rawtypes"
})
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<Context> contextProvider;

  public AuthRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(contextProvider.get());
  }

  public static AuthRepository_Factory create(Provider<Context> contextProvider) {
    return new AuthRepository_Factory(contextProvider);
  }

  public static AuthRepository newInstance(Context context) {
    return new AuthRepository(context);
  }
}
