package com.albert.common.http;

import androidx.annotation.NonNull;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 6/16/21.
 *      Desc         : 5转1
 * </pre>
 */
public interface HttpApiCast5to1Callback<X1, X2, X3, X4, X5, D> {
    /**
     * 在onSuc之前回调，注意线程。在这里处理转换代码
     */
    D onSucBefore(X1 serverData1, X2 serverData2, X3 serverData3, X4 serverData4, X5 serverData5);

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
