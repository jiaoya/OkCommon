package com.albert.common.manualapp;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.albert.common.activity.ActivityManager;
import com.albert.okutils.ProcessUtils;
import com.albert.okutils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-04-28.
 *      Desc         : 核心Application,主模块继承，这是手动处理方式
 * </pre>
 */
public abstract class CoreApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "CoreApplication";
    private static CoreApplication sInstance;
    private ArrayList<LogicAppliactionWrapper> mLogicList;
    private HashMap<String, ArrayList<LogicAppliactionWrapper>> mLogicClassMap;

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        init();
        initializeLogic();
        instantiateLogic();
    }

    public static CoreApplication getApplication() {
        return sInstance;
    }

    private void init() {
        // 注册activity回调
        registerActivityLifecycleCallbacks(this);
        Utils.init(this);
        mLogicClassMap = new HashMap<>();
    }

    /**
     * 初始化逻辑appliaciton，在此方法里可以掉用registerApplicationLogic()
     */
    protected abstract void initializeLogic();

    /**
     * 注册逻辑appliaciton
     *
     * @param processName 进程名
     * @param priority    优先级
     * @param logicClass  类名
     * @return
     */
    protected boolean registerApplicationLogic(String processName, int priority, @NonNull Class<? extends LogicApplication> logicClass) {
        boolean result = false;
        if (null != mLogicClassMap) {
            ArrayList<LogicAppliactionWrapper> tempList = mLogicClassMap.get(processName);
            if (null == tempList) {
                tempList = new ArrayList<>();
                mLogicClassMap.put(processName, tempList);
            }
            if (tempList.size() > 0) {
                for (LogicAppliactionWrapper priorityLogicWrapper : tempList) {
                    if (logicClass.getName().equals(priorityLogicWrapper.logicClass.getName())) {
                        throw new RuntimeException(logicClass.getName() + " has registered.");
                    }
                }
            }
            LogicAppliactionWrapper priorityLogicWrapper = new LogicAppliactionWrapper(priority, logicClass);
            tempList.add(priorityLogicWrapper);
        }
        return result;
    }


    /**
     * 实例化逻辑application和调用逻辑application的oncrate()
     */
    private void instantiateLogic() {
        if (null != mLogicClassMap) {
           mLogicList = mLogicClassMap.get(ProcessUtils.getCurrentProcessName());
        }
        if (null != mLogicList && mLogicList.size() > 0) {
            Collections.sort(mLogicList);
            for (LogicAppliactionWrapper priorityLogicWrapper : mLogicList) {
                if (null != priorityLogicWrapper) {
                    try {
                        priorityLogicWrapper.instance = priorityLogicWrapper.logicClass.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if (null != priorityLogicWrapper.instance) {
                        priorityLogicWrapper.instance.setApplication(this);
                        priorityLogicWrapper.instance.onCreate();
                    }
                }
            }
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if (null != mLogicList && mLogicList.size() > 0) {
            for (LogicAppliactionWrapper priorityLogicWrapper : mLogicList) {
                if (null != priorityLogicWrapper && null != priorityLogicWrapper.instance) {
                    priorityLogicWrapper.instance.onTerminate();
                }
            }
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (null != mLogicList && mLogicList.size() > 0) {
            for (LogicAppliactionWrapper priorityLogicWrapper : mLogicList) {
                if (null != priorityLogicWrapper && null != priorityLogicWrapper.instance) {
                    priorityLogicWrapper.instance.onLowMemory();
                }
            }
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (null != mLogicList && mLogicList.size() > 0) {
            for (LogicAppliactionWrapper priorityLogicWrapper : mLogicList) {
                if (null != priorityLogicWrapper && null != priorityLogicWrapper.instance) {
                    priorityLogicWrapper.instance.onTrimMemory(level);
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null != mLogicList && mLogicList.size() > 0) {
            for (LogicAppliactionWrapper priorityLogicWrapper : mLogicList) {
                if (null != priorityLogicWrapper && null != priorityLogicWrapper.instance) {
                    priorityLogicWrapper.instance.onConfigurationChanged(newConfig);
                }
            }
        }
    }


    /*------------------------------ActivityLifecycleCallbacks 回调--------------------------------*/
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        // activity 创建
        ActivityManager.getInstance().addActivity(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        // activity 销毁
        ActivityManager.getInstance().removeActivity(activity);
    }

}
