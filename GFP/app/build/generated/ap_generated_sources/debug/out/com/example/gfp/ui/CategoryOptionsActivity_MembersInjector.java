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
public final class CategoryOptionsActivity_MembersInjector implements MembersInjector<CategoryOptionsActivity> {
  private final Provider<SessionManager> sessionProvider;

  public CategoryOptionsActivity_MembersInjector(Provider<SessionManager> sessionProvider) {
    this.sessionProvider = sessionProvider;
  }

  public static MembersInjector<CategoryOptionsActivity> create(
      Provider<SessionManager> sessionProvider) {
    return new CategoryOptionsActivity_MembersInjector(sessionProvider);
  }

  @Override
  public void injectMembers(CategoryOptionsActivity instance) {
    injectSession(instance, sessionProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.CategoryOptionsActivity.session")
  public static void injectSession(CategoryOptionsActivity instance, SessionManager session) {
    instance.session = session;
  }
}
