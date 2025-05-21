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
public final class UserCategoryRepository_Factory implements Factory<UserCategoryRepository> {
  private final Provider<SessionManager> sessionManagerProvider;

  public UserCategoryRepository_Factory(Provider<SessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public UserCategoryRepository get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static UserCategoryRepository_Factory create(
      Provider<SessionManager> sessionManagerProvider) {
    return new UserCategoryRepository_Factory(sessionManagerProvider);
  }

  public static UserCategoryRepository newInstance(SessionManager sessionManager) {
    return new UserCategoryRepository(sessionManager);
  }
}
