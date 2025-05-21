package com.example.gfp.data.repository;

import com.example.gfp.data.session.SessionManager;
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
public final class UserRepository_Factory implements Factory<UserRepository> {
  private final Provider<SessionManager> sessionManagerProvider;

  public UserRepository_Factory(Provider<SessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public UserRepository get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static UserRepository_Factory create(Provider<SessionManager> sessionManagerProvider) {
    return new UserRepository_Factory(sessionManagerProvider);
  }

  public static UserRepository newInstance(SessionManager sessionManager) {
    return new UserRepository(sessionManager);
  }
}
