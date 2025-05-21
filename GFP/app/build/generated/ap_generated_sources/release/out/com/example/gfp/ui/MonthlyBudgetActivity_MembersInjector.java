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
public final class MonthlyBudgetActivity_MembersInjector implements MembersInjector<MonthlyBudgetActivity> {
  private final Provider<SessionManager> sessionProvider;

  public MonthlyBudgetActivity_MembersInjector(Provider<SessionManager> sessionProvider) {
    this.sessionProvider = sessionProvider;
  }

  public static MembersInjector<MonthlyBudgetActivity> create(
      Provider<SessionManager> sessionProvider) {
    return new MonthlyBudgetActivity_MembersInjector(sessionProvider);
  }

  @Override
  public void injectMembers(MonthlyBudgetActivity instance) {
    injectSession(instance, sessionProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.MonthlyBudgetActivity.session")
  public static void injectSession(MonthlyBudgetActivity instance, SessionManager session) {
    instance.session = session;
  }
}
