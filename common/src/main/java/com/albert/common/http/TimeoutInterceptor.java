package com.albert.common.http;


import com.albert.okutils.NullUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 7/20/21.
 *      Desc         : 可以自定义某个请求的超时时间 要用：Retrofit  @Headers({"CONNECT_TIMEOUT:600000", "READ_TIMEOUT:600000", "WRITE_TIMEOUT:600000"})
 * </pre>
 */
public class TimeoutInterceptor implements Interceptor {

    public static final String CONNECT_TIMEOUT = "CONNECT_TIMEOUT";
    public static final String READ_TIMEOUT = "READ_TIMEOUT";
    public static final String WRITE_TIMEOUT = "WRITE_TIMEOUT";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        int connectTimeout = chain.connectTimeoutMillis();
        int readTimeout = chain.readTimeoutMillis();
        int writeTimeout = chain.writeTimeoutMillis();

        Request request = chain.request();
        String connectNew = request.header(CONNECT_TIMEOUT);
        String readNew = request.header(READ_TIMEOUT);
        String writeNew = request.header(WRITE_TIMEOUT);

        if (NullUtil.notEmpty(connectNew)) {
            connectTimeout = Integer.parseInt(connectNew);
        }
        if (NullUtil.notEmpty(readNew)) {
            readTimeout = Integer.parseInt(readNew);
        }
        if (NullUtil.notEmpty(writeNew)) {
            writeTimeout = Integer.parseInt(writeNew);
        }

        return chain
                .withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .withReadTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .withWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .proceed(request);
    }

}