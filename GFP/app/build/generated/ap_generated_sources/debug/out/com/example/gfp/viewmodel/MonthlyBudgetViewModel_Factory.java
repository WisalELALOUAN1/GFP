package com.example.gfp.viewmodel;

import com.example.gfp.data.repository.UserRepository;
import com.example.gfp.data.session.SessionManager;
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
    "rawtypes"
})
public final class MonthlyBudgetViewModel_Factory implements Factory<MonthlyBudgetViewModel> {
  private final Provider<UserRepository> userRepoProvider;

  private final Provider<SessionManager> sessionProvider;

  public MonthlyBudgetViewModel_Factory(Provider<UserRepository> userRepoProvider,
      Provider<SessionManager> sessionProvider) {
    this.userRepoProvider = userRepoProvider;
    this.sessionProvider = sessionProvider;
  }

  @Override
  public MonthlyBudgetViewModel get() {
    return newInstance(userRepoProvider.get(), sessionProvider.get());
  }

  public static MonthlyBudgetViewModel_Factory create(Provider<UserRepository> userRepoProvider,
      Provider<SessionManager> sessionProvider) {
    return new MonthlyBudgetViewModel_Factory(userRepoProvider, sessionProvider);
  }

  public static MonthlyBudgetViewModel newInstance(UserRepository userRepo,
      SessionManager session) {
    return new MonthlyBudgetViewModel(userRepo, session);
  }
}
