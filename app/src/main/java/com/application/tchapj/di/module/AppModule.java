package com.application.tchapj.di.module;

import android.content.Context;
import android.util.Log;

import com.application.tchapj.App;
import com.application.tchapj.http.APIService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/24
 */
@Module
public class AppModule {

    // 默认超时时间 单位/秒
    private static final int DEFAULT_TIME_OUT = 10;

    private Context context;

    private String baseUrl;

    public AppModule(App context, String baseUrl){
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){

        //加入网络数据拦截日志
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("DOAING",message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(logInterceptor)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public APIService provideAPIService(){
        return provideRetrofit().create(APIService.class);
    }
}
