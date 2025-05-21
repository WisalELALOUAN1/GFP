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
public final class WelcomeActivity_MembersInjector implements MembersInjector<WelcomeActivity> {
  private final Provider<SessionManager> sessionProvider;

  public WelcomeActivity_MembersInjector(Provider<SessionManager> sessionProvider) {
    this.sessionProvider = sessionProvider;
  }

  public static MembersInjector<WelcomeActivity> create(Provider<SessionManager> sessionProvider) {
    return new WelcomeActivity_MembersInjector(sessionProvider);
  }

  @Override
  public void injectMembers(WelcomeActivity instance) {
    injectSession(instance, sessionProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.WelcomeActivity.session")
  public static void injectSession(WelcomeActivity instance, SessionManager session) {
    instance.session = session;
  }
}
