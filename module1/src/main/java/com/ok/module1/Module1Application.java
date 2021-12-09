package com.ok.module1;

import android.app.Application;

import androidx.annotation.NonNull;

import com.albert.okutils.Utils;
import com.facebook.stetho.common.LogUtil;
import com.xiaojinzi.component.anno.ModuleAppAnno;
import com.xiaojinzi.component.application.IApplicationLifecycle;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 11/3/21.
 *      Desc         :
 * </pre>
 */
@ModuleAppAnno
public class Module1Application implements IApplicationLifecycle {

    @Override
    public void onCreate(@NonNull Application app) {
        LogUtil.e("Module1Application");
    }

    @Override
    public void onDestroy() {

    }

}