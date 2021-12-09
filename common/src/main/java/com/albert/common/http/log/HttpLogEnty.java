package com.albert.common.http.log;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/9/16.
 *      Desc         : httplog实体
 * </pre>
 */
public class HttpLogEnty {

    public HttpLogEnty() {
        path = new StringBuilder();
    }

    public StringBuilder path;
    public String content;

}
