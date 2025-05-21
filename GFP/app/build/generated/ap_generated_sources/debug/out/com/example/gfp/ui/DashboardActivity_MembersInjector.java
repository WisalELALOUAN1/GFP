package com.example.gfp.ui;

import com.example.gfp.data.session.SessionManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DashboardActivity_MembersInjector implements MembersInjector<DashboardActivity> {
  private final Provider<SessionManager> sessionManagerProvider;

  public DashboardActivity_MembersInjector(Provider<SessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  public static MembersInjector<DashboardActivity> create(
      Provider<SessionManager> sessionManagerProvider) {
    return new DashboardActivity_MembersInjector(sessionManagerProvider);
  }

  @Override
  public void injectMembers(DashboardActivity instance) {
    injectSessionManager(instance, sessionManagerProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.DashboardActivity.sessionManager")
  public static void injectSessionManager(DashboardActivity instance,
      SessionManager sessionManager) {
    instance.sessionManager = sessionManager;
  }
}
