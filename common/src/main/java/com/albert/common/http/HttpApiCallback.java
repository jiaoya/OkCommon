package com.albert.common.http;

import androidx.annotation.NonNull;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-12-07.
 *      Desc         :
 * </pre>
 */
public interface HttpApiCallback<T> {
    /**
     * 在成功回调onSuc之前，注意线程
     */
    default void onSucBefore(T t) {

    }

    /**
     * 成功
     *
     * @param t
     */
    void onSuc(@NonNull T t);

    /**
     * 失败
     *
     * @param e
     */
    void onError(@NonNull HttpErrorException e);

    /**
     * 无论成功失败都走
     */
    default void onComplete() {

    }
}
