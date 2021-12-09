package com.albert.common.eventbus;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020-01-03.
 *      Desc         : 事件承载体
 * </pre>
 */
public class EbMsgEvent {

    public String key;
    public Object data;

    public EbMsgEvent(String key) {
        this.key = key;
    }

    public EbMsgEvent(String key, Object data) {
        this.key = key;
        this.data = data;
    }

    /**
     * 获取key
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
