package com.albert.common.activity;

import android.app.Activity;

import java.util.Stack;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-10.
 *      Desc         : activity管理
 * </pre>
 */
public final class ActivityManager {

    private Stack<Activity> activityStack; // 保存activity的栈
    private static ActivityManager instance; // 当前类的实例

    /**
     * 返回当前类的实例（单例模式）
     */
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    private ActivityManager() {
        if (null == activityStack) {
            activityStack = new Stack<>();
        }
    }

    /**
     * 获取Activity任务栈
     *
     * @return activity stack
     */
    public Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 添加Activity到堆栈,入栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        if (!activityStack.contains(activity)) {
            activityStack.add(activity);
        }
    }

    /**
     * Activity出栈
     *
     * @param activity Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /***
     * 获取指定类名的Activity实例
     * @param cls
     * @return
     */
    public Activity getActivity(Class<?> cls) {
        Activity activity = null;
        if (activityStack != null) {
            for (Activity a : activityStack) {
                if (a.getClass().equals(cls)) {
                    activity = a;
                }
            }
        }
        return activity;
    }

    /**
     * 获取指定类名的Activity实例
     *
     * @param clsName getSimpleName
     * @return
     */
    public Activity getActivity(String clsName) {
        if (activityStack != null) {
            for (Activity a : activityStack) {
                if (a.getClass().getSimpleName().equals(clsName)) {
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        finishActivity(activityStack.lastElement());
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activityStack == null || activityStack.empty()) {
            return;
        }
        if (activity != null && !activity.isDestroyed()) {
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack == null || activityStack.empty()) {
            return;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack == null || activityStack.empty()) {
            return;
        }
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 移除固定activity之外的所有activity
     *
     * @param activity 固定activity
     */
    public void finishExceptActivity(Activity activity) {
        if (activityStack == null || activityStack.empty()) {
            return;
        }
        for (Activity tActivity : activityStack) {
            if (activity != tActivity) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 移除固定activity之外的所有activity
     *
     * @param cls 固定activity
     */
    public void finishExceptActivity(Class<?> cls) {
        if (activityStack == null || activityStack.empty()) {
            return;
        }
        for (Activity activity : activityStack) {
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

}
