package com.albert.common.http;

import androidx.annotation.NonNull;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-12-27.
 *      Desc         :
 * </pre>
 */
public interface HttpApiCallback3<T, K, Z> {

    /**
     * 在成功回调onSuc之前，注意线程
     */
    default void onSucBefore(@NonNull T t, K k, Z z) {

    }

    void onSuc(@NonNull T t, K k, Z z);

    void onError(@NonNull HttpErrorException e);

    /**
     * 无论成功失败都走
     */
    default void onComplete() {

    }
}
