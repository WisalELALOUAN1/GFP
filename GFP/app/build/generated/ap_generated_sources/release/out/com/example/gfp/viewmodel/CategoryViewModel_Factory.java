package com.example.gfp.viewmodel;

import android.app.Application;
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
public final class CategoryViewModel_Factory implements Factory<CategoryViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public CategoryViewModel_Factory(Provider<Application> applicationProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.applicationProvider = applicationProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public CategoryViewModel get() {
    return newInstance(applicationProvider.get(), sessionManagerProvider.get());
  }

  public static CategoryViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new CategoryViewModel_Factory(applicationProvider, sessionManagerProvider);
  }

  public static CategoryViewModel newInstance(Application application,
      SessionManager sessionManager) {
    return new CategoryViewModel(application, sessionManager);
  }
}
