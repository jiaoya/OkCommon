package com.albert.common.http;

import androidx.annotation.NonNull;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 4/8/21.
 *      Desc         : 四转1
 * </pre>
 */
public interface HttpApiCast4to1Callback<X1, X2, X3, X4, D> {

    /**
     * 在onSuc之前回调，注意线程。在这里处理转换代码
     */
    D onSucBefore(X1 serverData1, X2 serverData2, X3 serverData3, X4 serverData4);

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