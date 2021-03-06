package com.example.prashant.androidrestexercise.DepInjection;

import android.content.Context;

import com.example.prashant.androidrestexercise.BuildConfig;
import com.example.prashant.androidrestexercise.Logic.BaseProcess;
import com.example.prashant.androidrestexercise.Logic.BaseUseCase;
import com.example.prashant.androidrestexercise.R;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DIModule {
    private Context con;

    public DIModule(Context context) {
        con = context;
    }

    @Provides
    @Singleton
    public BaseUseCase provideCoreUseCase(BaseProcess coreApi) {
        return new BaseUseCase(coreApi);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor);
        } else {
            okHttpClientBuilder
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS);
        }

        return okHttpClientBuilder.build();
    }

    @Provides
    @Singleton
    public BaseProcess provideCoreApi(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(con.getApplicationContext().getString(R.string.base_url))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(BaseProcess.class);
    }

}
