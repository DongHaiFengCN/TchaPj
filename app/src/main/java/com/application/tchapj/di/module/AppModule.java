package com.application.tchapj.di.module;

import android.content.Context;
import android.util.Log;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.di.component.AppComponent;
import com.application.tchapj.http.APIService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/24
 */

public class AppModule implements AppComponent {

    // 默认超时时间 单位/秒
    private static final int DEFAULT_TIME_OUT = 10;

    private Context context;

    public AppModule(App context){
        this.context = context;

    }


    public Context provideContext(){
        return context;
    }


    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build();
    }


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


    public APIService provideAPIService(){
        return provideRetrofit().create(APIService.class);
    }


    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Retrofit getRetrofit() {
        return provideRetrofit();
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        return provideOkHttpClient();
    }

    @Override
    public APIService getAPIService() {
         return provideRetrofit().create(APIService.class);
    }
}
