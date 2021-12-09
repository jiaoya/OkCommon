package com.albert.common.http;

import com.google.gson.Gson;
import com.albert.common.gson.GsonHelper;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * RequestBody 工具
 *
 * @author : jiaoya.
 * @Copyright : Copyright (c) 2018.
 * @Created Time : 2018/4/23.
 */
public class RequestBodyUtils {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static RequestBodyBuilder newBuilder(Object key, Object value) {
        return new RequestBodyBuilder(key, value);
    }

    public static RequestBodyBuilder newBuilder(Object key, String value) {
        return new RequestBodyBuilder(key, value);
    }

    public static RequestBodyBuilder newBuilder() {
        return new RequestBodyBuilder();
    }

    public static class RequestBodyBuilder {
        HashMap hashMap = new HashMap();

        public RequestBodyBuilder() {

        }

        public RequestBodyBuilder(Object key, Object value) {
            append(key, value);
        }

        public RequestBodyBuilder(Object key, String value) {
            append(key, value);
        }

        public RequestBodyBuilder append(Object key, Object value) {
            if (null == value || "".equals(String.valueOf(value))) {
                return this;
            } else {
                hashMap.put(String.valueOf(key), value);
                return this;
            }
        }

        public RequestBodyBuilder append(Object key, String value) {
            if (null == value || "".equals(value)) {
                return this;
            } else {
                hashMap.put(String.valueOf(key), value);
                return this;
            }
        }

        public RequestBody build() {
            return RequestBody.create(JSON, new Gson().toJson(hashMap));
        }
    }

    public static RequestBody create(String data) {
        return RequestBody.create(JSON, data);
    }

    public static RequestBody create(Object data) {
        String jsonData = GsonHelper.getInstance().toJson(data);
        return RequestBody.create(JSON, jsonData);
    }
}
