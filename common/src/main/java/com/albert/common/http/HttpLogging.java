package com.albert.common.http;


import com.albert.common.http.log.HttpLogEnty;
import com.albert.okutils.LogUtils;
import com.albert.okutils.NullUtil;
import com.albert.okutils.StringUtils;
import com.albert.okutils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-12-12.
 *      Desc         : okhttp 网络打印
 * </pre>
 */
public class HttpLogging {

    public static List<String> mLogData;
    public static List<HttpLogEnty> mLogDatas;

    public static HttpLoggingInterceptor setLogging() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            private StringBuilder mPrintMsgLog = new StringBuilder();
            private StringBuilder mMsg = new StringBuilder();
            private HttpLogEnty logEnty;

            @Override
            public void log(String message) {

                if (message.contains("--> GET") || message.contains("--> POST")) {
                    mPrintMsgLog.setLength(0);
                    mPrintMsgLog.append("Start:\n");

                    if (logEnty == null) {
                        logEnty = new HttpLogEnty();
                    }

                    mMsg.setLength(0);
                    mMsg.append("Start:\n")
                            .append(TimeUtils.millis2String(System.currentTimeMillis())).append("\n");
                }

                if (message.contains("http/1.1") || message.contains("http/1.2")) {
                    mPrintMsgLog.append("Header：");
                    mMsg.append("Header：");
                }

                if (message.contains("--> END POST") || message.contains("--> END GET")) {
                    mPrintMsgLog.append("\nResult：");
                    mMsg.append("\nResult：");
                }

                if (NullUtil.notEmpty(message)) {

                    mPrintMsgLog.append("\n");
                    mPrintMsgLog.append(message);

                    mMsg.append("\n");
                    if (message.contains("{")) { // json数据
                        mMsg.append(StringUtils.formatJsonString(message));
                    } else {
                        mMsg.append(message);
                    }

                    // 请求地址
                    if (message.contains("--> GET http") || message.contains("--> POST http")) {
                        if (logEnty != null) {
                            logEnty.path.append("开始时间：").append(TimeUtils.millis2String(System.currentTimeMillis()))
                                    .append("\n").append(message);
                        }
                    }
                }

                if (message.contains("<-- END HTTP")) {
                    mPrintMsgLog.append("    \n");
                    if (mPrintMsgLog.length() > 4096) {// 防止打印不全，分开打印
                        String a1 = mPrintMsgLog.substring(0, mPrintMsgLog.length() / 2);
                        String a2 = mPrintMsgLog.substring(mPrintMsgLog.length() / 2, mPrintMsgLog.length());
                        LogUtils.e("HTTP----", a1 + "\n");
                        LogUtils.e("HTTP----", a2 + "\n");
                    } else {
                        LogUtils.e("HTTP----", mPrintMsgLog.toString() + "\n");
                        
                    }

                    //addLog(mMessage);

                    if (logEnty != null) {
                        logEnty.content = mMsg.toString();
                        addLog(logEnty);
                        logEnty.path.delete(0, logEnty.path.length());
                    }
                }

            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    private static void addLog(HttpLogEnty logMsg) {
        if (!HttpClient.getConfig().isTest()) {
            return;
        }

        if (mLogDatas == null) {
            mLogDatas = new ArrayList<>();
        }
        mLogDatas.add(0, logMsg);
    }

    private static void addLog(StringBuilder logMsg) {
        logMsg.insert(0, TimeUtils.millis2String(System.currentTimeMillis()) + ":\n");
        if (mLogData == null) {
            mLogData = new ArrayList<>();
        }
        mLogData.add(0, logMsg.toString());
    }

    public static List<String> getLogData() {
        if (mLogData != null) {
            mLogData = new ArrayList<>();
        }
        return mLogData;
    }

    /**
     * 获取格式化后的 网络日志
     *
     * @return
     */
    public static List<HttpLogEnty> getLogDatas() {
        if (mLogData == null) {
            mLogData = new ArrayList<>();
        }
        return mLogDatas;
    }

    public static void clearLog() {
        if (mLogData != null) {
            mLogData.clear();
        }

        if (mLogDatas != null) {
            mLogDatas.clear();
        }
    }

}
