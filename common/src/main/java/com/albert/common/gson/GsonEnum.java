package com.albert.common.gson;

import com.google.gson.JsonElement;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/5/27.
 *      Desc         : 枚举类型的序列化 转换
 * </pre>
 */
public interface GsonEnum<E extends Enum<E>> {

    /**
     * 序列化
     *
     * @return
     */
    Object serialize();

    /**
     * 反序列化
     *
     * @param jsonEnum
     * @return
     */
    E deserialize(JsonElement jsonEnum);

    /**
     * 获取到值
     *
     * @param o   值
     * @param <T> 值
     * @return 枚举
     */
    <T> E convert(T o);

}
