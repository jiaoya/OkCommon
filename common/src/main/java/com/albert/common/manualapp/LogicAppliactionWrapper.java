package com.albert.common.manualapp;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-04-28.
 *      Desc         : 用来对LogicAPPliaction包装和优先级比较
 * </pre>
 */
public class LogicAppliactionWrapper implements Comparable<LogicAppliactionWrapper> {

    public int priority = 0;
    public Class<? extends LogicApplication> logicClass = null;
    public LogicApplication instance;

    public LogicAppliactionWrapper(int priority, Class<? extends LogicApplication> logicClass) {
        this.priority = priority;
        this.logicClass = logicClass;
    }

    @Override
    public int compareTo(LogicAppliactionWrapper o) {
        return o.priority - this.priority;
    }
}
