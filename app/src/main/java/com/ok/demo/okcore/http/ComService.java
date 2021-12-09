package com.ok.demo.okcore.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 9/21/21.
 *      Desc         :
 * </pre>
 */
interface ComService {

    @FormUrlEncoded
    @POST("baike")
    Observable<DataBean> getData(@FieldMap Map<String, String> Body);


    @POST("front/cosmetic/qry/analysis")
    Observable<Result> analysis(@Body RequestBody body);


} 