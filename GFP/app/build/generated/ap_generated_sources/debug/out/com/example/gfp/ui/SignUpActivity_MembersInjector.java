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
public final class SignUpActivity_MembersInjector implements MembersInjector<SignUpActivity> {
  private final Provider<SessionManager> sessionProvider;

  public SignUpActivity_MembersInjector(Provider<SessionManager> sessionProvider) {
    this.sessionProvider = sessionProvider;
  }

  public static MembersInjector<SignUpActivity> create(Provider<SessionManager> sessionProvider) {
    return new SignUpActivity_MembersInjector(sessionProvider);
  }

  @Override
  public void injectMembers(SignUpActivity instance) {
    injectSession(instance, sessionProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.SignUpActivity.session")
  public static void injectSession(SignUpActivity instance, SessionManager session) {
    instance.session = session;
  }
}
