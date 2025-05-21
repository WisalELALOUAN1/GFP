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
public final class ChargesActivity_MembersInjector implements MembersInjector<ChargesActivity> {
  private final Provider<SessionManager> sessionProvider;

  public ChargesActivity_MembersInjector(Provider<SessionManager> sessionProvider) {
    this.sessionProvider = sessionProvider;
  }

  public static MembersInjector<ChargesActivity> create(Provider<SessionManager> sessionProvider) {
    return new ChargesActivity_MembersInjector(sessionProvider);
  }

  @Override
  public void injectMembers(ChargesActivity instance) {
    injectSession(instance, sessionProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.ChargesActivity.session")
  public static void injectSession(ChargesActivity instance, SessionManager session) {
    instance.session = session;
  }
}
