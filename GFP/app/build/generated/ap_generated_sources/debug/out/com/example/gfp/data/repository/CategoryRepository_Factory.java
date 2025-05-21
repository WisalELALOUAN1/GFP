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
public final class CategoryRepository_Factory implements Factory<CategoryRepository> {
  @Override
  public CategoryRepository get() {
    return newInstance();
  }

  public static CategoryRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CategoryRepository newInstance() {
    return new CategoryRepository();
  }

  private static final class InstanceHolder {
    private static final CategoryRepository_Factory INSTANCE = new CategoryRepository_Factory();
  }
}
