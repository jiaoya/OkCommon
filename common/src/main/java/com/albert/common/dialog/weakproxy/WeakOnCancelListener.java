package com.albert.common.dialog.weakproxy;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/12/17.
 *      Desc         :
 * </pre>
 */
public class WeakOnCancelListener implements DialogInterface.OnCancelListener {

    private WeakReference<DialogInterface.OnCancelListener> mRef;

    public WeakOnCancelListener(DialogInterface.OnCancelListener real) {
        this.mRef = new WeakReference<>(real);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        DialogInterface.OnCancelListener real = mRef.get();
        if (real != null) {
            real.onCancel(dialog);
        }
    }
}
