package com.albert.common.http;

import androidx.annotation.NonNull;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 3/11/21.
 *      Desc         : 三转1
 * </pre>
 */
public interface HttpApiCast3to1Callback<X, Y, Z, D> {

    /**
     * 在onSuc之前回调，注意线程。在这里处理转换代码
     */
    D onSucBefore(X serverDatax, Y serverDatay, Z serverDataz);

    /**
     * 成功
     *
     * @param data
     */
    void onSuc(@NonNull D data);

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