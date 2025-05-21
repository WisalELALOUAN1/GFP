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
public final class GoalsListActivity_MembersInjector implements MembersInjector<GoalsListActivity> {
  private final Provider<SessionManager> sessionProvider;

  public GoalsListActivity_MembersInjector(Provider<SessionManager> sessionProvider) {
    this.sessionProvider = sessionProvider;
  }

  public static MembersInjector<GoalsListActivity> create(
      Provider<SessionManager> sessionProvider) {
    return new GoalsListActivity_MembersInjector(sessionProvider);
  }

  @Override
  public void injectMembers(GoalsListActivity instance) {
    injectSession(instance, sessionProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.GoalsListActivity.session")
  public static void injectSession(GoalsListActivity instance, SessionManager session) {
    instance.session = session;
  }
}
