package com.example.gfp.ui;

import com.example.gfp.data.repository.UserCategoryRepository;
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
public final class AnalyseActivity_MembersInjector implements MembersInjector<AnalyseActivity> {
  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<UserCategoryRepository> userCategoryRepositoryProvider;

  public AnalyseActivity_MembersInjector(Provider<SessionManager> sessionManagerProvider,
      Provider<UserCategoryRepository> userCategoryRepositoryProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
    this.userCategoryRepositoryProvider = userCategoryRepositoryProvider;
  }

  public static MembersInjector<AnalyseActivity> create(
      Provider<SessionManager> sessionManagerProvider,
      Provider<UserCategoryRepository> userCategoryRepositoryProvider) {
    return new AnalyseActivity_MembersInjector(sessionManagerProvider, userCategoryRepositoryProvider);
  }

  @Override
  public void injectMembers(AnalyseActivity instance) {
    injectSessionManager(instance, sessionManagerProvider.get());
    injectUserCategoryRepository(instance, userCategoryRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.AnalyseActivity.sessionManager")
  public static void injectSessionManager(AnalyseActivity instance, SessionManager sessionManager) {
    instance.sessionManager = sessionManager;
  }

  @InjectedFieldSignature("com.example.gfp.ui.AnalyseActivity.userCategoryRepository")
  public static void injectUserCategoryRepository(AnalyseActivity instance,
      UserCategoryRepository userCategoryRepository) {
    instance.userCategoryRepository = userCategoryRepository;
  }
}
