package com.example.gfp.viewmodel;

import com.example.gfp.data.repository.AuthRepository;
import com.example.gfp.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class UserViewModel_Factory implements Factory<UserViewModel> {
  private final Provider<AuthRepository> authRepoProvider;

  private final Provider<UserRepository> userRepoProvider;

  public UserViewModel_Factory(Provider<AuthRepository> authRepoProvider,
      Provider<UserRepository> userRepoProvider) {
    this.authRepoProvider = authRepoProvider;
    this.userRepoProvider = userRepoProvider;
  }

  @Override
  public UserViewModel get() {
    return newInstance(authRepoProvider.get(), userRepoProvider.get());
  }

  public static UserViewModel_Factory create(Provider<AuthRepository> authRepoProvider,
      Provider<UserRepository> userRepoProvider) {
    return new UserViewModel_Factory(authRepoProvider, userRepoProvider);
  }

  public static UserViewModel newInstance(AuthRepository authRepo, UserRepository userRepo) {
    return new UserViewModel(authRepo, userRepo);
  }
}
