package com.albert.common.http;


import android.annotation.SuppressLint;

import com.albert.okutils.AppUtils;
import com.albert.okutils.DeviceUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-10.
 *      Desc         :
 * </pre>
 */
public class HeaderConfigInterceptor implements Interceptor {

    private static ConcurrentHashMap<String, String> headers = new ConcurrentHashMap<>();
    private static StringBuffer userAgent = new StringBuffer();

    public HeaderConfigInterceptor() {
        init();
    }

    @SuppressLint("MissingPermission")
    private void init() {
        // bckid; iPhone; app版本; 设备型号; 设备系统版本; 设备唯一识别码; 渠道编码
        if (userAgent.length() == 0) {
            userAgent.append("bckid; ")
                    .append("Android; ")
                    .append(AppUtils.getAppVersionName()).append("; ")
                    .append(DeviceUtils.getDeviceName()).append("; ")
                    .append(DeviceUtils.getSDKVersionName()).append("; ")
                    .append(DeviceUtils.getUUID()).append("; ");
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        configHeader(builder);
        Request requestWithUserAgent = builder.build();
        return chain.proceed(requestWithUserAgent);
    }

    private void configHeader(Request.Builder builder) {
        // addHeader(builder, "Authorization", LoginStatus.getToken());//重要
        addHeader(builder, "user-agent-bckid", userAgent.toString().replace("\n", ""));
        Iterator<String> keyIterator = headers.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            addHeader(builder, key, headers.get(key));
        }
        builder.header("User-Agent", "BCKID/Http");
    }

    private void addHeader(Request.Builder builder, String key, Object value) {
        if (null != value) {
            builder.removeHeader(key);
            builder.addHeader(key, String.valueOf(value));
        }
    }

    /**
     * 设置header的值
     *
     * @param key
     * @param value
     */
    public static void setHeader(String key, String value) {
        if (headers.containsKey(key)) {
            headers.replace(key, value);
        } else {
            headers.put(key, value);
        }
    }

    /**
     * 得到所以自定义的head的值
     *
     * @return
     */
    public static ConcurrentHashMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * 设置 userAgent 的值
     *
     * @param value
     */
    public static void putUserAgent(String value) {
        userAgent.append(value).append("; ");
    }

    public static StringBuffer getUserAgent() {
        return userAgent;
    }
}
