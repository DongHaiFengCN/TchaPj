package com.application.tchapj.di.component;

import android.content.Context;
import com.application.tchapj.App;
import com.application.tchapj.di.module.AppModule;
import com.application.tchapj.di.module.AppModule_ProvideAPIServiceFactory;
import com.application.tchapj.di.module.AppModule_ProvideContextFactory;
import com.application.tchapj.di.module.AppModule_ProvideOkHttpClientFactory;
import com.application.tchapj.di.module.AppModule_ProvideRetrofitFactory;
import com.application.tchapj.http.APIService;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<Context> provideContextProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<OkHttpClient> provideOkHttpClientProvider;

  private Provider<APIService> provideAPIServiceProvider;

  private DaggerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideContextProvider =
        DoubleCheck.provider(AppModule_ProvideContextFactory.create(builder.appModule));

    this.provideRetrofitProvider =
        DoubleCheck.provider(AppModule_ProvideRetrofitFactory.create(builder.appModule));

    this.provideOkHttpClientProvider =
        DoubleCheck.provider(AppModule_ProvideOkHttpClientFactory.create(builder.appModule));

    this.provideAPIServiceProvider =
        DoubleCheck.provider(AppModule_ProvideAPIServiceFactory.create(builder.appModule));
  }

  @Override
  public void inject(App app) {
    MembersInjectors.<App>noOp().injectMembers(app);
  }

  @Override
  public Context getContext() {
    return provideContextProvider.get();
  }

  @Override
  public Retrofit getRetrofit() {
    return provideRetrofitProvider.get();
  }

  @Override
  public OkHttpClient getOkHttpClient() {
    return provideOkHttpClientProvider.get();
  }

  @Override
  public APIService getAPIService() {
    return provideAPIServiceProvider.get();
  }

  public static final class Builder {
    private AppModule appModule;

    private Builder() {}

    public AppComponent build() {
      if (appModule == null) {
        throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }
  }
}
