package com.albert.common.eventbus;

import androidx.annotation.NonNull;

import com.albert.common.BuildConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/3/14.
 *      Desc         : EventBus的注册和发送进行分装
 * </pre>
 */
public class EbUtils {

    private static EventBus mEventBus;

    /**
     * 设置默认的EventBus 实例，如果不是设置，默认去sdk的
     *
     * @param mEventBus
     */
    public static void setDefauleEventBus(EventBus mEventBus) {
        EbUtils.mEventBus = mEventBus;
    }

    /**
     * 获取默认的EventBus实例
     *
     * @return
     */
    public static EventBus getDefault() {
        if (mEventBus == null) {
            mEventBus = EventBus.getDefault();
        }
        return mEventBus;
    }


    /**
     * 初始化时调用
     *
     * @param subscriberInfoIndexs
     */
    public static void init(SubscriberInfoIndex... subscriberInfoIndexs) {
        EventBusBuilder busBuilder = EventBus.builder();
        if (subscriberInfoIndexs != null) {
            for (SubscriberInfoIndex index : subscriberInfoIndexs) {
                busBuilder.addIndex(index);
            }
        }
        busBuilder.throwSubscriberException(BuildConfig.DEBUG);
        busBuilder.installDefaultEventBus();
    }

    /**
     * 注册
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    /**
     * 总是新注册，不论有没有注册过
     *
     * @param subscriber
     */
    public static void registerAlways(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 取消注册
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送事件
     *
     * @param key
     */
    public static void post(@NonNull String key) {
        EbMsgEvent messageEvent = new EbMsgEvent(key);
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 发送粘性事件
     *
     * @param key
     * @param data
     */
    public static void post(@NonNull String key, @NonNull Object data) {
        EbMsgEvent messageEvent = new EbMsgEvent(key, data);
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 发送粘性事件
     *
     * @param key
     */
    public static void postSticky(@NonNull String key) {
        EbMsgEvent messageEvent = new EbMsgEvent(key);
        EventBus.getDefault().postSticky(messageEvent);
    }

    /**
     * 发送粘性事件
     *
     * @param key
     * @param data
     */
    public static void postSticky(@NonNull String key, @NonNull Object data) {
        EbMsgEvent messageEvent = new EbMsgEvent(key, data);
        EventBus.getDefault().postSticky(messageEvent);
    }

}
