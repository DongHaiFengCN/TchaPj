package com.application.tchapj.base;

import com.application.tchapj.App;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BaseMvpPresenter_Factory<V extends BaseMvpView>
    implements Factory<BaseMvpPresenter<V>> {
  private final MembersInjector<BaseMvpPresenter<V>> baseMvpPresenterMembersInjector;

  private final Provider<App> appProvider;

  public BaseMvpPresenter_Factory(
      MembersInjector<BaseMvpPresenter<V>> baseMvpPresenterMembersInjector,
      Provider<App> appProvider) {
    assert baseMvpPresenterMembersInjector != null;
    this.baseMvpPresenterMembersInjector = baseMvpPresenterMembersInjector;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public BaseMvpPresenter<V> get() {
    return MembersInjectors.injectMembers(
        baseMvpPresenterMembersInjector, new BaseMvpPresenter<V>(appProvider.get()));
  }

  public static <V extends BaseMvpView> Factory<BaseMvpPresenter<V>> create(
      MembersInjector<BaseMvpPresenter<V>> baseMvpPresenterMembersInjector,
      Provider<App> appProvider) {
    return new BaseMvpPresenter_Factory<V>(baseMvpPresenterMembersInjector, appProvider);
  }
}
