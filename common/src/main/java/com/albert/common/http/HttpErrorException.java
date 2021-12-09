package com.albert.common.http;


import com.albert.okutils.NullUtil;

import java.io.IOException;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2018.
 *      Author       : jiaoya.
 *      Created Time : 2018/8/24.
 *      Desc         : 网络请求异常处理model，code msg  错误码
 * </pre>
 */
public class HttpErrorException extends IOException {

    private static final String UNKNOW_ERROR = "";// 网络接口错误

    public int code = -1;
    public String displayMsg = "";

    public HttpErrorException(Throwable throwable) {
        super(throwable);
        code = -1;
    }

    public HttpErrorException(String code, String errorMsg) {
        this(Integer.parseInt(code), errorMsg);
    }

    public HttpErrorException(int code, String errorMsg) {

        if (NullUtil.notEmpty(code)) {
            this.code = code;
        } else {
            this.code = -1;
        }

        if (NullUtil.notEmpty(errorMsg)) {
            this.displayMsg = errorMsg;
        } else {
            this.displayMsg = UNKNOW_ERROR;
        }
    }

    public HttpErrorException(Throwable throwable, String code) {
        this(throwable, Integer.parseInt(code));
    }

    public HttpErrorException(Throwable throwable, int code) {
        super(throwable);
        if (NullUtil.notEmpty(code)) {
            this.code = code;
        } else {
            this.code = -1;
        }
        if (NullUtil.notEmpty(throwable.getMessage())) {
            this.displayMsg = throwable.getMessage();
        } else {
            this.displayMsg = UNKNOW_ERROR;
        }
    }

    public HttpErrorException(Throwable throwable, String code, String errorMsg) {
        this(throwable, Integer.parseInt(code), errorMsg);
    }

    public HttpErrorException(Throwable throwable, int code, String errorMsg) {
        super(throwable);
        if (NullUtil.notEmpty(code)) {
            this.code = code;
        } else {
            this.code = -1;
        }
        if (NullUtil.notEmpty(errorMsg)) {
            this.displayMsg = errorMsg;
        } else {
            this.displayMsg = UNKNOW_ERROR;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMsg() {
        return displayMsg;
    }

    public void setDisplayMsg(String msg) {
        this.displayMsg = msg;
    }
}
