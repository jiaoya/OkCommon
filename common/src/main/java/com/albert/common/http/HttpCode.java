package com.albert.common.http;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/8/13.
 *      Desc         : code 常量
 * </pre>
 */
public class HttpCode {
    public static int H_1 = -1;
    public static int H_200 = 200;
    public static int H_400 = 400;
    public static int H_401 = 401;
    public static int H_500 = 500;

    public static int H_10000 = 10000;
    public static int H_10001 = 10001;
    public static int H_10002 = 10002;
    public static int H_10003 = 10003;
    public static int H_10004 = 10004;

    /**
     * 未知异常
     */
    public static int UNKNOWN_ERROR = H_1;
    /**
     * 正常
     */
    public static int NORMAL = H_200;
    /**
     * 接口异常
     */
    public static int API_ERROR = H_400;
    /**
     * 未登录
     */
    public static int NO_LOGIN = H_401;
    /**
     * 服务器异常
     */
    public static int SERVER_ERROR = H_500;
    /**
     * 空数据
     */
    public static int NULL_ERROR = H_10000;
    /**
     * 网络异常
     */
    public static int NERWORK_ERROR = H_10001;
    /**
     * JSON解析异常
     */
    public static int JSON_ERROR = H_10002;
    /**
     * 加解密异常
     */
    public static int ENCRYPT_ERROR = H_10003;
    /**
     * 类型转换异常
     */
    public static int CLASS_CAST_ERROR = H_10004;
}
