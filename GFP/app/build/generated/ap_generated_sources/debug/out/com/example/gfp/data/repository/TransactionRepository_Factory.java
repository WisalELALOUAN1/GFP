package com.example.gfp.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class TransactionRepository_Factory implements Factory<TransactionRepository> {
  @Override
  public TransactionRepository get() {
    return newInstance();
  }

  public static TransactionRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TransactionRepository newInstance() {
    return new TransactionRepository();
  }

  private static final class InstanceHolder {
    private static final TransactionRepository_Factory INSTANCE = new TransactionRepository_Factory();
  }
}
