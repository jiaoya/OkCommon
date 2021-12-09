package com.albert.common.http;

import androidx.annotation.NonNull;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 3/8/21.
 *      Desc         : 此方法是把从服务端返回的T数据 转成 R数据，转换在onSucBefore里，成功返回onSuc（R）
 * </pre>
 */
public interface HttpApiCastCallback<T, R> {
    /**
     * 在onSuc之前回调，注意线程。在这里处理转换代码
     */
    R onSucBefore(T serverData);

    /**
     * 成功
     *
     * @param data
     */
    void onSuc(@NonNull R data);

    /**
     * 失败
     *
     * @param e
     */
    default void onError(@NonNull HttpErrorException e) {

    }

    /**
     * 无论成功失败都走
     */
    default void onComplete() {

    }
} 