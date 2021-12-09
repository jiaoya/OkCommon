package com.albert.common.manualapp;

import android.content.res.Configuration;

import androidx.annotation.NonNull;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-04-28.
 *      Desc         : 逻辑appliaction,各个，模块调用
 * </pre>
 */
public class LogicApplication {

    protected CoreApplication mApplication;

    public LogicApplication() {
    }

    public void setApplication(@NonNull CoreApplication application) {
        mApplication = application;
    }

    public void onCreate() {
    }

    public void onTerminate() {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
