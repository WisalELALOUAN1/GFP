package com.example.gfp.ui;

import com.example.gfp.data.repository.TransactionRepository;
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
public final class TransactionsActivity_MembersInjector implements MembersInjector<TransactionsActivity> {
  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public TransactionsActivity_MembersInjector(Provider<SessionManager> sessionManagerProvider,
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  public static MembersInjector<TransactionsActivity> create(
      Provider<SessionManager> sessionManagerProvider,
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new TransactionsActivity_MembersInjector(sessionManagerProvider, transactionRepositoryProvider);
  }

  @Override
  public void injectMembers(TransactionsActivity instance) {
    injectSessionManager(instance, sessionManagerProvider.get());
    injectTransactionRepository(instance, transactionRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.example.gfp.ui.TransactionsActivity.sessionManager")
  public static void injectSessionManager(TransactionsActivity instance,
      SessionManager sessionManager) {
    instance.sessionManager = sessionManager;
  }

  @InjectedFieldSignature("com.example.gfp.ui.TransactionsActivity.transactionRepository")
  public static void injectTransactionRepository(TransactionsActivity instance,
      TransactionRepository transactionRepository) {
    instance.transactionRepository = transactionRepository;
  }
}
