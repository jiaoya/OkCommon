package com.albert.common.http;

import androidx.annotation.NonNull;

import com.albert.common.gson.GsonHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-15.
 *      Desc         : RetrofitClient
 * </pre>
 */
public class RetrofitClient {

    /**
     * 单例示例
     */
    private static RetrofitClient instance;

    private Retrofit retrofitDataWraper;
    private Retrofit retrofitString;
    private Retrofit retrofiObject;

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                instance = new RetrofitClient();
            }
        }
        return instance;
    }


    /**
     * 加载默认的网络客户端，默认：code msg data 自定义DataWraper解析
     *
     * @param baseUrl
     * @param service
     * @param <T>
     * @return
     */
    public <T> T getRetrofitService(String baseUrl, Class<T> service) {
        return getRetrofit(baseUrl).create(service);
    }

    /**
     * 加载 返回string类型的客户端 ScalarsConverterFactory解析
     *
     * @param baseUrl
     * @param service
     * @param <T>
     * @return
     */
    public <T> T getRetrofitStringService(String baseUrl, Class<T> service) {
        return getRetrofitString(baseUrl).create(service);
    }

    /**
     * 加载返回对象 GsonConverterFactory 解析
     *
     * @param baseUrl
     * @param service
     * @param <T>
     * @return
     */
    public <T> T getRetrofitObjectService(String baseUrl, Class<T> service) {
        return getRetrofitObject(baseUrl).create(service);
    }


    private Retrofit getRetrofit(@NonNull String baseUrl) {
        if (retrofitDataWraper == null) {
            retrofitDataWraper = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(HttpClient.INSTANCE.getOkHttpClient())
                    .addConverterFactory(DataWraper.getFactory())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitDataWraper;
    }

    /**
     * 默认：code msg data 自定义DataWraper解析
     *
     * @param retrofitDataWraper
     */
    public void setRetrofitDataWraper(Retrofit retrofitDataWraper) {
        this.retrofitDataWraper = retrofitDataWraper;
    }

    private Retrofit getRetrofitString(@NonNull String baseUrl) {
        if (retrofitString == null) {
            retrofitString = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(HttpClient.INSTANCE.getOkHttpClient())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitString;
    }

    /**
     * 使用ScalarsConverterFactory
     *
     * @param retrofitString
     */
    public void setRetrofitString(Retrofit retrofitString) {
        this.retrofitString = retrofitString;
    }

    private Retrofit getRetrofitObject(@NonNull String baseUrl) {
        if (retrofiObject == null) {
            retrofiObject = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(HttpClient.INSTANCE.getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(GsonHelper.getInstance()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        HttpClient.Builder builder = new HttpClient.Builder();
        return retrofiObject;
    }

    /**
     * GsonConverterFactory
     *
     * @param retrofiObject
     */
    public void setRetrofiObject(Retrofit retrofiObject) {
        this.retrofiObject = retrofiObject;
    }

}
