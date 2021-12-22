package com.albert.common.http;

import com.albert.common.http.cookie.PersistentCookieJar;
import com.albert.common.http.cookie.PersistentCookieStore;
import com.albert.okutils.Utils;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-10.
 *      Desc         :
 * </pre>
 */
public enum HttpClient {

    INSTANCE;
    private static Builder config;
    private OkHttpClient client;
    private PersistentCookieStore persistentCookieStore;

    HttpClient() {
        init();
    }

    public OkHttpClient getOkHttpClient() {
        init();
        return client;
    }

    /**
     * 清理cookie
     */
    public void clearCookie() {
        if (persistentCookieStore != null) {
            persistentCookieStore.removeAll();
        }
    }

    private void init() {
        persistentCookieStore = new PersistentCookieStore(Utils.getAppContext());
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.connectTimeout(getConfig().connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(getConfig().writeTimeout, TimeUnit.SECONDS)
                .readTimeout(getConfig().readTimeout, TimeUnit.SECONDS)
                .cookieJar(new PersistentCookieJar(new CookieManager(persistentCookieStore, CookiePolicy.ACCEPT_ALL)))
                .addInterceptor(new TimeoutInterceptor())
                .addInterceptor(new HeaderConfigInterceptor());


        // 添加拦截器
        if (getConfig().interceptors != null && getConfig().interceptors.size() > 0) {
            for (Interceptor interceptor : getConfig().interceptors) {
                okBuilder.addInterceptor(interceptor);
            }
        }
        // 测试环境才使用的拦截器
        if (getConfig().isTest) {
            okBuilder.addNetworkInterceptor(new StethoInterceptor())
                    .addNetworkInterceptor(HttpLogging.setLogging());

            if (getConfig().testInterceptors != null && getConfig().testInterceptors.size() > 0) {
                for (Interceptor interceptor : getConfig().testInterceptors) {
                    okBuilder.addInterceptor(interceptor);
                }
            }
        }

        client = okBuilder.build();
    }

    /**
     * 设置配置
     *
     * @param config
     */
    public static void setConfig(Builder config) {
        HttpClient.config = config;
    }

    public static Builder getConfig() {
        if (config == null) {
            config = new Builder();
        }
        return config;
    }

    public static final class Builder {

        private boolean isTest = true;
        private long connectTimeout = 30;
        private long writeTimeout = 30;
        private long readTimeout = 30;
        //private  cookieJar cookieJar;
        private HashSet<Interceptor> interceptors;
        private HashSet<Interceptor> testInterceptors;

        public Builder() {

        }

        /**
         * 设置是否为 测试状态
         *
         * @param test
         * @return
         */
        public Builder setTestStatue(boolean test) {
            isTest = test;
            return this;
        }

        public boolean isTest() {
            return isTest;
        }

        /**
         * 设置链接超时时间
         *
         * @param connectTimeout 秒
         * @return
         */
        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /**
         * 写入超时时间
         *
         * @param writeTimeout 秒
         * @return
         */
        public Builder setWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        /**
         * 读取超时时间
         *
         * @param readTimeout 秒
         * @return
         */
        public Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * 设置拦截器
         *
         * @param interceptor
         * @return
         */
        public Builder setInterceptors(Interceptor interceptor) {
            if (this.interceptors == null) {
                this.interceptors = new HashSet<>();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        /**
         * 只是只有在 测试或是debug状态下才加载的拦截器
         *
         * @param testInterceptor
         * @return
         */
        public Builder setTestInterceptors(Interceptor testInterceptor) {
            if (this.testInterceptors == null) {
                this.testInterceptors = new HashSet<>();
            }
            this.testInterceptors.add(testInterceptor);
            return this;
        }

    }

}
