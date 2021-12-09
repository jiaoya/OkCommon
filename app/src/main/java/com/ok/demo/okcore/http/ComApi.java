package com.ok.demo.okcore.http;


import com.albert.common.http.RequestBodyUtils;
import com.albert.common.http.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 9/21/21.
 *      Desc         :
 * </pre>
 */
public class ComApi {

//    public static final ComService COM_SERVICE = RetrofitClient.getInstance().getRetrofitObjectService("Config.HOST", ComService.class);
//
//    public static Observable<DataBean> getData(String name) {
//        Map<String, String> bodys = new HashMap<String, String>();
//        bodys.put("src", name);
//        return COM_SERVICE.getData(bodys);
//    }


    /**
     * 获取公共token
     *
     * @return
     */
    public static Observable<Result> analysis(String data) {
        RequestBody body = RequestBodyUtils.newBuilder()
                .append("cosmetics", data)
                .build();
        return HttpApiClient.COM_SERVICE.analysis(body);
    }


} 