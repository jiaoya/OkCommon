package com.albert.common.http;

import android.net.ParseException;

import com.facebook.stetho.common.LogUtil;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import retrofit2.HttpException;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-15.
 *      Desc         : 服务端接口的所有异常进行统一处理和转换
 * </pre>
 */
public class HttpApiExceptionEngine {

    private static final String HTTP_EXCEPTION_NAME = "接口异常：";
    private static HttpApiExceptionCallback IhttpApiExceptionCallback;

    public static HttpErrorException handleException(String code, Object data, String errorMsg) {
        return httpCodeDeal(code, data, errorMsg);
    }

    public static HttpErrorException handleException(Throwable e) {
        if (IhttpApiExceptionCallback != null) {
            IhttpApiExceptionCallback.onHttpExceptionDeal(e);
        }
        HttpErrorException ex;
        try {
            if (e instanceof HttpException) {// HTTP错误
                HttpException httpException = (HttpException) e;
                int code = httpException.code();

                ex = new HttpErrorException(e, code);
                if (code == HttpCode.API_ERROR) {
                    // 这里服务端如果报错，错误信息统一写在400里，这里和结果是分开的，
                    // 当然也可以不,在200里进行统一处理，这时就要也要在onNext里处理这个类，可以继承ServerApiObserve类在写个观察者
                    String errorBodyString = httpException.response().errorBody().string();
                    DataWraper dataWraper = new Gson().fromJson(errorBodyString, DataWraper.class);
                    ex = new HttpErrorException(e, HttpCode.API_ERROR);
                    ex.setDisplayMsg(dataWraper.message);
                } else if (code == HttpCode.NO_LOGIN) {
                    // 一般登录问题处理/禁止访问
                    deal401();
                    String errorBodyString = httpException.response().errorBody().string();
                    DataWraper dataWraper = new Gson().fromJson(errorBodyString, DataWraper.class);
                    ex = new HttpErrorException(e, HttpCode.NO_LOGIN);
                    ex.setDisplayMsg(dataWraper.message);

                } else if (code == 403) {
                    ex = new HttpErrorException(e, 403);
                    ex.setDisplayMsg(HTTP_EXCEPTION_NAME + 403);

                } else if (code == 404) {
                    ex.setDisplayMsg(HTTP_EXCEPTION_NAME + 404);

                } else if (code == 408) {
                    ex = new HttpErrorException(e, 408);
                    ex.setDisplayMsg(HTTP_EXCEPTION_NAME + 408);

                } else if (code == 429) {
                    ex = new HttpErrorException(e, 429);
                } else if (code == 504) {
                    ex = new HttpErrorException(e, 504);
                    ex.setDisplayMsg(HTTP_EXCEPTION_NAME + 504);

                } else if (code == 500) {
                    ex = new HttpErrorException(e, 500);
                    ex.setDisplayMsg(HTTP_EXCEPTION_NAME + 500);

                } else if (code == 502) {
                    ex = new HttpErrorException(e, 502);
                    ex.setDisplayMsg(HTTP_EXCEPTION_NAME + 502);

                } else if (code == 503) {
                    ex = new HttpErrorException(e, 503);
                    ex.setDisplayMsg(HTTP_EXCEPTION_NAME + 503);

                } else {
                    ex = new HttpErrorException(e, HttpCode.NERWORK_ERROR, "网络不给力哦~");
                }
                return ex;

            } else if (e instanceof SocketTimeoutException) {    //服务器无响应请求超时
                ex = new HttpErrorException(e, HttpCode.NERWORK_ERROR, "服务器无响应请求超时");
                return ex;

            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException) {
                // 均视为解析错误
                ex = new HttpErrorException(e, HttpCode.JSON_ERROR, "json解析异常");
                return ex;

            } else if (e instanceof BadPaddingException
                    || e instanceof IllegalBlockSizeException
                    || e instanceof UnsupportedEncodingException) {
                ex = new HttpErrorException(e, HttpCode.ENCRYPT_ERROR, "加密解密异常");
                return ex;

            } else if (e instanceof NoSuchAlgorithmException
                    || e instanceof NoSuchPaddingException
                    || e instanceof InvalidAlgorithmParameterException
                    || e instanceof InvalidKeyException) {
                // 加密解密初始化异常
                ex = new HttpErrorException(e, HttpCode.ENCRYPT_ERROR, "加密初始化错误");
                return ex;

            } else if (e instanceof ConnectException
                    || e instanceof UnknownHostException) {
                ex = new HttpErrorException(e, HttpCode.NERWORK_ERROR, "网络不给力哦~");
                return ex;

            } else if (e instanceof NullPointerException) {
                ex = new HttpErrorException(e);
                return ex;

            } else if (e instanceof ClassCastException) {
                ex = new HttpErrorException(e, HttpCode.CLASS_CAST_ERROR, "类型转换异常");
                return ex;
            } else {
                // 未知错误
                ex = new HttpErrorException(e, HttpCode.UNKNOWN_ERROR, "");// 未知错误
                LogUtil.e("httperror:", e.getMessage());
                return ex;
            }
        } catch (Exception e1) {
            ex = new HttpErrorException(e, HttpCode.UNKNOWN_ERROR, "");// 未知错误
            LogUtil.e("httperror:", e1.getMessage());
            return ex;
        }
    }

    private static HttpErrorException httpCodeDeal(String code, Object data, String errorMsg) {
        if (IhttpApiExceptionCallback != null) {
            IhttpApiExceptionCallback.onHttpCodeDeal(code, errorMsg);
        }
        HttpErrorException ex;
        if ("400".equals(code)) {
            // 这里服务端如果报错，错误信息统一写在400里，这里和结果是分开的，
            ex = new HttpErrorException(HttpCode.API_ERROR, errorMsg);
        } else if ("401".equals(code)) {
            // 一般登录问题处理/禁止访问
            ex = new HttpErrorException(HttpCode.NO_LOGIN, errorMsg);
            deal401();
        } else if ("403".equals(code)) {
            ex = new HttpErrorException(403, errorMsg);
        } else if ("404".equals(code)) {
            ex = new HttpErrorException(404, HTTP_EXCEPTION_NAME + 404);

        } else if ("408".equals(code)) {
            ex = new HttpErrorException(408, HTTP_EXCEPTION_NAME + 408);
        } else if ("429".equals(code)) {
            ex = new HttpErrorException(429, errorMsg);
        } else if ("500".equals(code)) {
            ex = new HttpErrorException(HttpCode.SERVER_ERROR, HTTP_EXCEPTION_NAME + 500);
        } else if ("504".equals(code)) {
            ex = new HttpErrorException(504, HTTP_EXCEPTION_NAME + 504);

        } else if ("502".equals(code)) {
            ex = new HttpErrorException(502, HTTP_EXCEPTION_NAME + 502);

        } else if ("503".equals(code)) {
            if (IhttpApiExceptionCallback != null) {
                IhttpApiExceptionCallback.onDeal503(data);
            }
            ex = new HttpErrorException(503, HTTP_EXCEPTION_NAME + 503);

        } else {
            ex = new HttpErrorException(HttpCode.NERWORK_ERROR, "网络不给力哦~");
        }
        return ex;
    }

    /**
     * 处理401 问题
     */
    private static void deal401() {
        // 一般登录问题处理/禁止访问
        if (IhttpApiExceptionCallback != null) {
            IhttpApiExceptionCallback.onDeal401();
        }
    }

    /**
     * 设置异常处理回调
     *
     * @param ihttpApiExceptionCallback
     */
    public static void sethttpApiExceptionCallback(HttpApiExceptionCallback ihttpApiExceptionCallback) {
        IhttpApiExceptionCallback = ihttpApiExceptionCallback;
    }


}
