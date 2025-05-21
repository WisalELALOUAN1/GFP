package com.example.gfp;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.example.gfp.data.repository.AuthRepository;
import com.example.gfp.data.repository.CategoryRepository;
import com.example.gfp.data.repository.GoalRepository;
import com.example.gfp.data.repository.TransactionRepository;
import com.example.gfp.data.repository.UserCategoryRepository;
import com.example.gfp.data.repository.UserRepository;
import com.example.gfp.data.session.SessionManager;
import com.example.gfp.di.AppModule;
import com.example.gfp.di.AppModule_ProvideContextFactory;
import com.example.gfp.ui.AnalyseActivity;
import com.example.gfp.ui.AnalyseActivity_MembersInjector;
import com.example.gfp.ui.CategoryOptionsActivity;
import com.example.gfp.ui.CategoryOptionsActivity_MembersInjector;
import com.example.gfp.ui.ChargesActivity;
import com.example.gfp.ui.ChargesActivity_MembersInjector;
import com.example.gfp.ui.DashboardActivity;
import com.example.gfp.ui.DashboardActivity_MembersInjector;
import com.example.gfp.ui.DefineGoalActivity;
import com.example.gfp.ui.DefineGoalActivity_MembersInjector;
import com.example.gfp.ui.GoalsListActivity;
import com.example.gfp.ui.GoalsListActivity_MembersInjector;
import com.example.gfp.ui.LoginActivity;
import com.example.gfp.ui.LoginActivity_MembersInjector;
import com.example.gfp.ui.ModifyGoalActivity;
import com.example.gfp.ui.ModifyGoalActivity_MembersInjector;
import com.example.gfp.ui.MonthlyBudgetActivity;
import com.example.gfp.ui.MonthlyBudgetActivity_MembersInjector;
import com.example.gfp.ui.ReportsActivity;
import com.example.gfp.ui.ReportsActivity_MembersInjector;
import com.example.gfp.ui.SignUpActivity;
import com.example.gfp.ui.SignUpActivity_MembersInjector;
import com.example.gfp.ui.TransactionsActivity;
import com.example.gfp.ui.TransactionsActivity_MembersInjector;
import com.example.gfp.ui.WelcomeActivity;
import com.example.gfp.ui.WelcomeActivity_MembersInjector;
import com.example.gfp.viewmodel.CategoryViewModel;
import com.example.gfp.viewmodel.CategoryViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.gfp.viewmodel.ChargesViewModel;
import com.example.gfp.viewmodel.ChargesViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.gfp.viewmodel.MonthlyBudgetViewModel;
import com.example.gfp.viewmodel.MonthlyBudgetViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.gfp.viewmodel.UserViewModel;
import com.example.gfp.viewmodel.UserViewModel_HiltModules_KeyModule_ProvideFactory;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerMyApplication_HiltComponents_SingletonC {
  private DaggerMyApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private AppModule appModule;

    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule(
        HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule) {
      Preconditions.checkNotNull(hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule);
      return this;
    }

    public MyApplication_HiltComponents.SingletonC build() {
      if (appModule == null) {
        this.appModule = new AppModule();
      }
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(appModule, applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements MyApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public MyApplication_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl(singletonCImpl);
    }
  }

  private static final class ActivityCBuilder implements MyApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements MyApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements MyApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements MyApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements MyApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements MyApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends MyApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends MyApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends MyApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends MyApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectAnalyseActivity(AnalyseActivity arg0) {
      injectAnalyseActivity2(arg0);
    }

    @Override
    public void injectCategoryOptionsActivity(CategoryOptionsActivity arg0) {
      injectCategoryOptionsActivity2(arg0);
    }

    @Override
    public void injectChargesActivity(ChargesActivity arg0) {
      injectChargesActivity2(arg0);
    }

    @Override
    public void injectDashboardActivity(DashboardActivity arg0) {
      injectDashboardActivity2(arg0);
    }

    @Override
    public void injectDefineGoalActivity(DefineGoalActivity arg0) {
      injectDefineGoalActivity2(arg0);
    }

    @Override
    public void injectGoalsListActivity(GoalsListActivity arg0) {
      injectGoalsListActivity2(arg0);
    }

    @Override
    public void injectLoginActivity(LoginActivity arg0) {
      injectLoginActivity2(arg0);
    }

    @Override
    public void injectModifyGoalActivity(ModifyGoalActivity arg0) {
      injectModifyGoalActivity2(arg0);
    }

    @Override
    public void injectMonthlyBudgetActivity(MonthlyBudgetActivity arg0) {
      injectMonthlyBudgetActivity2(arg0);
    }

    @Override
    public void injectReportsActivity(ReportsActivity arg0) {
      injectReportsActivity2(arg0);
    }

    @Override
    public void injectSignUpActivity(SignUpActivity arg0) {
      injectSignUpActivity2(arg0);
    }

    @Override
    public void injectTransactionsActivity(TransactionsActivity arg0) {
      injectTransactionsActivity2(arg0);
    }

    @Override
    public void injectWelcomeActivity(WelcomeActivity arg0) {
      injectWelcomeActivity2(arg0);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return ImmutableSet.<String>of(CategoryViewModel_HiltModules_KeyModule_ProvideFactory.provide(), ChargesViewModel_HiltModules_KeyModule_ProvideFactory.provide(), MonthlyBudgetViewModel_HiltModules_KeyModule_ProvideFactory.provide(), UserViewModel_HiltModules_KeyModule_ProvideFactory.provide());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private AnalyseActivity injectAnalyseActivity2(AnalyseActivity instance) {
      AnalyseActivity_MembersInjector.injectSessionManager(instance, singletonCImpl.sessionManagerProvider.get());
      AnalyseActivity_MembersInjector.injectUserCategoryRepository(instance, singletonCImpl.userCategoryRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private CategoryOptionsActivity injectCategoryOptionsActivity2(
        CategoryOptionsActivity instance) {
      CategoryOptionsActivity_MembersInjector.injectSession(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private ChargesActivity injectChargesActivity2(ChargesActivity instance) {
      ChargesActivity_MembersInjector.injectSession(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private DashboardActivity injectDashboardActivity2(DashboardActivity instance) {
      DashboardActivity_MembersInjector.injectSessionManager(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private DefineGoalActivity injectDefineGoalActivity2(DefineGoalActivity instance) {
      DefineGoalActivity_MembersInjector.injectSessionManager(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private GoalsListActivity injectGoalsListActivity2(GoalsListActivity instance) {
      GoalsListActivity_MembersInjector.injectSession(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private LoginActivity injectLoginActivity2(LoginActivity instance) {
      LoginActivity_MembersInjector.injectSession(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private ModifyGoalActivity injectModifyGoalActivity2(ModifyGoalActivity instance) {
      ModifyGoalActivity_MembersInjector.injectSessionManager(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private MonthlyBudgetActivity injectMonthlyBudgetActivity2(MonthlyBudgetActivity instance) {
      MonthlyBudgetActivity_MembersInjector.injectSession(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private ReportsActivity injectReportsActivity2(ReportsActivity instance) {
      ReportsActivity_MembersInjector.injectSessionManager(instance, singletonCImpl.sessionManagerProvider.get());
      ReportsActivity_MembersInjector.injectTransactionRepository(instance, singletonCImpl.transactionRepositoryProvider.get());
      ReportsActivity_MembersInjector.injectGoalRepository(instance, singletonCImpl.goalRepositoryProvider.get());
      ReportsActivity_MembersInjector.injectUserCategoryRepository(instance, singletonCImpl.userCategoryRepositoryProvider.get());
      ReportsActivity_MembersInjector.injectCategoryRepository(instance, singletonCImpl.categoryRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private SignUpActivity injectSignUpActivity2(SignUpActivity instance) {
      SignUpActivity_MembersInjector.injectSession(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private TransactionsActivity injectTransactionsActivity2(TransactionsActivity instance) {
      TransactionsActivity_MembersInjector.injectSessionManager(instance, singletonCImpl.sessionManagerProvider.get());
      TransactionsActivity_MembersInjector.injectTransactionRepository(instance, singletonCImpl.transactionRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private WelcomeActivity injectWelcomeActivity2(WelcomeActivity instance) {
      WelcomeActivity_MembersInjector.injectSession(instance, singletonCImpl.sessionManagerProvider.get());
      return instance;
    }
  }

  private static final class ViewModelCImpl extends MyApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<CategoryViewModel> categoryViewModelProvider;

    private Provider<ChargesViewModel> chargesViewModelProvider;

    private Provider<MonthlyBudgetViewModel> monthlyBudgetViewModelProvider;

    private Provider<UserViewModel> userViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.categoryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.chargesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.monthlyBudgetViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.userViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
    }

    @Override
    public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
      return ImmutableMap.<String, Provider<ViewModel>>of("com.example.gfp.viewmodel.CategoryViewModel", ((Provider) categoryViewModelProvider), "com.example.gfp.viewmodel.ChargesViewModel", ((Provider) chargesViewModelProvider), "com.example.gfp.viewmodel.MonthlyBudgetViewModel", ((Provider) monthlyBudgetViewModelProvider), "com.example.gfp.viewmodel.UserViewModel", ((Provider) userViewModelProvider));
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.example.gfp.viewmodel.CategoryViewModel 
          return (T) new CategoryViewModel(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.sessionManagerProvider.get());

          case 1: // com.example.gfp.viewmodel.ChargesViewModel 
          return (T) new ChargesViewModel(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.userCategoryRepositoryProvider.get(), singletonCImpl.sessionManagerProvider.get());

          case 2: // com.example.gfp.viewmodel.MonthlyBudgetViewModel 
          return (T) new MonthlyBudgetViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.sessionManagerProvider.get());

          case 3: // com.example.gfp.viewmodel.UserViewModel 
          return (T) new UserViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends MyApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends MyApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends MyApplication_HiltComponents.SingletonC {
    private final AppModule appModule;

    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<Context> provideContextProvider;

    private Provider<SessionManager> sessionManagerProvider;

    private Provider<UserCategoryRepository> userCategoryRepositoryProvider;

    private Provider<TransactionRepository> transactionRepositoryProvider;

    private Provider<GoalRepository> goalRepositoryProvider;

    private Provider<CategoryRepository> categoryRepositoryProvider;

    private Provider<UserRepository> userRepositoryProvider;

    private Provider<AuthRepository> authRepositoryProvider;

    private SingletonCImpl(AppModule appModuleParam,
        ApplicationContextModule applicationContextModuleParam) {
      this.appModule = appModuleParam;
      this.applicationContextModule = applicationContextModuleParam;
      initialize(appModuleParam, applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final AppModule appModuleParam,
        final ApplicationContextModule applicationContextModuleParam) {
      this.provideContextProvider = DoubleCheck.provider(new SwitchingProvider<Context>(singletonCImpl, 1));
      this.sessionManagerProvider = DoubleCheck.provider(new SwitchingProvider<SessionManager>(singletonCImpl, 0));
      this.userCategoryRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserCategoryRepository>(singletonCImpl, 2));
      this.transactionRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TransactionRepository>(singletonCImpl, 3));
      this.goalRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<GoalRepository>(singletonCImpl, 4));
      this.categoryRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<CategoryRepository>(singletonCImpl, 5));
      this.userRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserRepository>(singletonCImpl, 6));
      this.authRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepository>(singletonCImpl, 7));
    }

    @Override
    public void injectMyApplication(MyApplication myApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.example.gfp.data.session.SessionManager 
          return (T) new SessionManager(singletonCImpl.provideContextProvider.get());

          case 1: // android.content.Context 
          return (T) AppModule_ProvideContextFactory.provideContext(singletonCImpl.appModule, ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.example.gfp.data.repository.UserCategoryRepository 
          return (T) new UserCategoryRepository(singletonCImpl.sessionManagerProvider.get());

          case 3: // com.example.gfp.data.repository.TransactionRepository 
          return (T) new TransactionRepository();

          case 4: // com.example.gfp.data.repository.GoalRepository 
          return (T) new GoalRepository(singletonCImpl.sessionManagerProvider.get());

          case 5: // com.example.gfp.data.repository.CategoryRepository 
          return (T) new CategoryRepository();

          case 6: // com.example.gfp.data.repository.UserRepository 
          return (T) new UserRepository(singletonCImpl.sessionManagerProvider.get());

          case 7: // com.example.gfp.data.repository.AuthRepository 
          return (T) new AuthRepository(singletonCImpl.provideContextProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
