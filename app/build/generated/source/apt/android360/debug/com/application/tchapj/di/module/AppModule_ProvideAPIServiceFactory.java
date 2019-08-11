package com.application.tchapj.di.module;

import com.application.tchapj.http.APIService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideAPIServiceFactory implements Factory<APIService> {
  private final AppModule module;

  public AppModule_ProvideAPIServiceFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public APIService get() {
    return Preconditions.checkNotNull(
        module.provideAPIService(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<APIService> create(AppModule module) {
    return new AppModule_ProvideAPIServiceFactory(module);
  }
}
