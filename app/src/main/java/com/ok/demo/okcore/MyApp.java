package com.ok.demo.okcore;

import android.app.Application;

import com.albert.okutils.LogUtils;
import com.albert.okutils.OaidUtils;
import com.albert.okutils.Utils;
import com.xiaojinzi.component.Component;
import com.xiaojinzi.component.Config;
import com.xiaojinzi.component.support.RxErrorIgnoreUtil;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 10/26/21.
 *      Desc         :
 * </pre>
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRouter();
        Utils.init(this);
        new OaidUtils(this, null);
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);

    }


    /**
     * 路由的初始化时间决定了 模块生命周期的加载时间
     */
    private void initRouter() {
        // 初始化
        Component.init(BuildConfig.DEBUG,
                Config.with(this)
                        .defaultScheme("ylt")
                        // optimizeInit 表示是否使用 ASM 字节码技术加载模块, 默认是 false
                        // 如果是 true，则下一步 Gradle 插件必选
                        // 如果是 false，则可以忽略下一步 Gradle 插件
                        .optimizeInit(true)
                        // 自动加载所有模块, 打开此开关后下面无需手动注册了
                        // 该配置需要 optimizeInit(true) 才会生效
                        .autoRegisterModule(true) // 1.7.9+
                        .build()
        );
        // 如果你依赖了 rx 版本,需要配置这句代码,否则删除这句
        RxErrorIgnoreUtil.ignoreError();
        // Debug 的包开启检查
        if (BuildConfig.DEBUG) {
            // 框架还带有检查重复的路由和重复的拦截器等功能,在 `debug` 的时候开启它
            Component.check();
        }
    }

}