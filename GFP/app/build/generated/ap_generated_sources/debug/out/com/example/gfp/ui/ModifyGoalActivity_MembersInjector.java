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
public final class ModifyGoalActivity_MembersInjector implements MembersInjector<ModifyGoalActivity> {
  private final Provider<SessionManager> sessionManagerProvider;

  public ModifyGoalActivity_MembersInjector(Provider<SessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  public static MembersInjector<ModifyGoalActivity> create(
      Provider<SessionManager> sessionManagerProvider) {
    return new ModifyGoalActivity_MembersInjector(sessionManagerProvider);
  }

  @Override
  public void injectMembers(ModifyGoalActivity instance) {
    injectSessionManager(instance, sessionManagerProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.ModifyGoalActivity.sessionManager")
  public static void injectSessionManager(ModifyGoalActivity instance,
      SessionManager sessionManager) {
    instance.sessionManager = sessionManager;
  }
}
