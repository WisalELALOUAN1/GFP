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
public final class DefineGoalActivity_MembersInjector implements MembersInjector<DefineGoalActivity> {
  private final Provider<SessionManager> sessionManagerProvider;

  public DefineGoalActivity_MembersInjector(Provider<SessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  public static MembersInjector<DefineGoalActivity> create(
      Provider<SessionManager> sessionManagerProvider) {
    return new DefineGoalActivity_MembersInjector(sessionManagerProvider);
  }

  @Override
  public void injectMembers(DefineGoalActivity instance) {
    injectSessionManager(instance, sessionManagerProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.DefineGoalActivity.sessionManager")
  public static void injectSessionManager(DefineGoalActivity instance,
      SessionManager sessionManager) {
    instance.sessionManager = sessionManager;
  }
}
