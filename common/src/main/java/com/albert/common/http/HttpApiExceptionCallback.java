package com.albert.common.http;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/4/15.
 *      Desc         : Http异常处理回调
 * </pre>
 */
public interface HttpApiExceptionCallback {

    /**
     * 异常回调
     *
     * @param e
     */
    public void onHttpExceptionDeal(Throwable e);

    /**
     * 解析回调
     *
     * @param code
     * @param errorMsg
     */
    public void onHttpCodeDeal(String code, String errorMsg);

    /**
     * 401回调
     */
    public void onDeal401();

    /**
     * 503回调
     */
    void onDeal503(Object data);

}
