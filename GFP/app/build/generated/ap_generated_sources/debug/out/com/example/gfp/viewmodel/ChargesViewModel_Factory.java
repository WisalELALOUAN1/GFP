package com.example.gfp.viewmodel;

import android.app.Application;
import com.example.gfp.data.repository.UserCategoryRepository;
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
public final class ChargesViewModel_Factory implements Factory<ChargesViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<UserCategoryRepository> repoProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public ChargesViewModel_Factory(Provider<Application> applicationProvider,
      Provider<UserCategoryRepository> repoProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.applicationProvider = applicationProvider;
    this.repoProvider = repoProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ChargesViewModel get() {
    return newInstance(applicationProvider.get(), repoProvider.get(), sessionManagerProvider.get());
  }

  public static ChargesViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<UserCategoryRepository> repoProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new ChargesViewModel_Factory(applicationProvider, repoProvider, sessionManagerProvider);
  }

  public static ChargesViewModel newInstance(Application application, UserCategoryRepository repo,
      SessionManager sessionManager) {
    return new ChargesViewModel(application, repo, sessionManager);
  }
}
