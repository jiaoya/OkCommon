package com.albert.common.dialog.weakproxy;

import android.content.DialogInterface;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/12/17.
 *      Desc         : 软引用，防止泄露
 * </pre>
 */
public class RefOnCancelListener implements DialogInterface.OnCancelListener {

    private SoftReference<DialogInterface.OnCancelListener> mRef;

    public RefOnCancelListener(DialogInterface.OnCancelListener real) {
        this.mRef = new SoftReference<>(real);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        DialogInterface.OnCancelListener real = mRef.get();
        if (real != null) {
            real.onCancel(dialog);
        }
    }
}
