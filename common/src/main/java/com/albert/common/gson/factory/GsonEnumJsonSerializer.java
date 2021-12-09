package com.albert.common.gson.factory;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.albert.common.gson.GsonEnum;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/5/27.
 *      Desc         :
 * </pre>
 */
public class GsonEnumJsonSerializer<E> implements JsonSerializer<E> {

    public GsonEnumJsonSerializer() {

    }

    @Override
    public JsonElement serialize(E src, Type typeOfSrc, JsonSerializationContext context) {
        JsonPrimitive jsonElement = null;
        if (src instanceof GsonEnum) {
            try {
                final Object serialize = ((GsonEnum) src).serialize();
                JsonPrimitive jsonPrimitive = new JsonPrimitive("");
                final Field value = jsonPrimitive.getClass().getDeclaredField("value");
                value.setAccessible(true);
                value.set(jsonPrimitive, serialize);
                jsonElement = jsonPrimitive;
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return jsonElement;
    }

    public E deserialize(JsonElement json, Type type) throws JsonParseException {
        final Class<?>[] interfaces = ((Class) type).getInterfaces();
        for (Class c : interfaces) {
            if (c.equals(GsonEnum.class)) {
                try {
                    GsonEnum[] objs = (GsonEnum[]) ((Class) type).getEnumConstants();
                    if (null != objs && objs.length > 0) {
                        /**
                         * 自定义枚举的实现接口后其内部的枚举对应的都是一个方法
                         * 默认取值任意一个枚举的实现方法就行
                         */
                        return (E) objs[0].deserialize(json);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
