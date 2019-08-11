package com.application.tchapj.di.component;

import android.content.Context;

import com.application.tchapj.App;
import com.application.tchapj.di.module.AppModule;
import com.application.tchapj.http.APIService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/24
 */
@Singleton
@Component(modules= AppModule.class)
public interface AppComponent {

    void inject(App app);

    Context getContext();

    Retrofit getRetrofit();

    OkHttpClient getOkHttpClient();

    APIService getAPIService();

}
