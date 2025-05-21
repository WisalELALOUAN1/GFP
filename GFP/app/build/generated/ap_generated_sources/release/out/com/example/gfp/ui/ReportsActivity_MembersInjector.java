package com.example.gfp.ui;

import com.example.gfp.data.repository.CategoryRepository;
import com.example.gfp.data.repository.GoalRepository;
import com.example.gfp.data.repository.TransactionRepository;
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
public final class ReportsActivity_MembersInjector implements MembersInjector<ReportsActivity> {
  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<GoalRepository> goalRepositoryProvider;

  private final Provider<UserCategoryRepository> userCategoryRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  public ReportsActivity_MembersInjector(Provider<SessionManager> sessionManagerProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<GoalRepository> goalRepositoryProvider,
      Provider<UserCategoryRepository> userCategoryRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.goalRepositoryProvider = goalRepositoryProvider;
    this.userCategoryRepositoryProvider = userCategoryRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
  }

  public static MembersInjector<ReportsActivity> create(
      Provider<SessionManager> sessionManagerProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<GoalRepository> goalRepositoryProvider,
      Provider<UserCategoryRepository> userCategoryRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    return new ReportsActivity_MembersInjector(sessionManagerProvider, transactionRepositoryProvider, goalRepositoryProvider, userCategoryRepositoryProvider, categoryRepositoryProvider);
  }

  @Override
  public void injectMembers(ReportsActivity instance) {
    injectSessionManager(instance, sessionManagerProvider.get());
    injectTransactionRepository(instance, transactionRepositoryProvider.get());
    injectGoalRepository(instance, goalRepositoryProvider.get());
    injectUserCategoryRepository(instance, userCategoryRepositoryProvider.get());
    injectCategoryRepository(instance, categoryRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.ReportsActivity.sessionManager")
  public static void injectSessionManager(ReportsActivity instance, SessionManager sessionManager) {
    instance.sessionManager = sessionManager;
  }

  @InjectedFieldSignature("com.example.gfp.ui.ReportsActivity.transactionRepository")
  public static void injectTransactionRepository(ReportsActivity instance,
      TransactionRepository transactionRepository) {
    instance.transactionRepository = transactionRepository;
  }

  @InjectedFieldSignature("com.example.gfp.ui.ReportsActivity.goalRepository")
  public static void injectGoalRepository(ReportsActivity instance, GoalRepository goalRepository) {
    instance.goalRepository = goalRepository;
  }

  @InjectedFieldSignature("com.example.gfp.ui.ReportsActivity.userCategoryRepository")
  public static void injectUserCategoryRepository(ReportsActivity instance,
      UserCategoryRepository userCategoryRepository) {
    instance.userCategoryRepository = userCategoryRepository;
  }

  @InjectedFieldSignature("com.example.gfp.ui.ReportsActivity.categoryRepository")
  public static void injectCategoryRepository(ReportsActivity instance,
      CategoryRepository categoryRepository) {
    instance.categoryRepository = categoryRepository;
  }
}
