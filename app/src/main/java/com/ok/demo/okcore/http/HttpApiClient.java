package com.ok.demo.okcore.http;

import com.albert.common.http.RetrofitClient;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-12-12.
 *      Desc         :
 * </pre>
 */
public class HttpApiClient {

    public static final String BASE_URL_TEST = "http://192.168.30.206:7000/";
    /**
     * com服务器地址-公共
     */
    // public static final String COM_URL = BASE_URL_TEST;

    /**
     * 公共
     */
    public static final ComService COM_SERVICE = RetrofitClient.getInstance().getRetrofitService(BASE_URL_TEST, ComService.class);

}
