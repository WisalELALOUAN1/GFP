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
public final class GoalRepository_Factory implements Factory<GoalRepository> {
  private final Provider<SessionManager> sessionManagerProvider;

  public GoalRepository_Factory(Provider<SessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public GoalRepository get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static GoalRepository_Factory create(Provider<SessionManager> sessionManagerProvider) {
    return new GoalRepository_Factory(sessionManagerProvider);
  }

  public static GoalRepository newInstance(SessionManager sessionManager) {
    return new GoalRepository(sessionManager);
  }
}
