package com.albert.common.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.albert.common.gson.factory.GsonEnumJsonSerializer;
import com.albert.common.gson.factory.GsonEnumTypeAdapterFactory;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/5/27.
 *      Desc         : 获取自定义的gson实例
 * </pre>
 */
public class GsonHelper {

    private GsonHelper() {
    }

    private static Gson instance;

    public static Gson getInstance() {
        if (instance == null) {
            instance = new GsonBuilder()
                    //当Map的key为复杂对象时,需要开启该方法
                    .enableComplexMapKeySerialization()
                    //当字段值为空或null时，依然对该字段进行转换
                    .serializeNulls()
                    //防止特殊字符出现乱码
                    .disableHtmlEscaping()
                    .registerTypeAdapterFactory(new GsonEnumTypeAdapterFactory())
                    .registerTypeAdapter(GsonEnum.class, new GsonEnumJsonSerializer<GsonEnum>())
                    .create();
        }
        return instance;
    }

    public static void setInstance(Gson instance) {
        GsonHelper.instance = instance;
    }

}
