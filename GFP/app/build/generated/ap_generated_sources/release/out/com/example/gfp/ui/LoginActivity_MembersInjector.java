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
public final class LoginActivity_MembersInjector implements MembersInjector<LoginActivity> {
  private final Provider<SessionManager> sessionProvider;

  public LoginActivity_MembersInjector(Provider<SessionManager> sessionProvider) {
    this.sessionProvider = sessionProvider;
  }

  public static MembersInjector<LoginActivity> create(Provider<SessionManager> sessionProvider) {
    return new LoginActivity_MembersInjector(sessionProvider);
  }

  @Override
  public void injectMembers(LoginActivity instance) {
    injectSession(instance, sessionProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.LoginActivity.session")
  public static void injectSession(LoginActivity instance, SessionManager session) {
    instance.session = session;
  }
}
