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
public class RefOnDismissListener implements DialogInterface.OnDismissListener {

    private SoftReference<DialogInterface.OnDismissListener> mRef;

    public RefOnDismissListener(DialogInterface.OnDismissListener real) {
        this.mRef = new SoftReference<>(real);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        DialogInterface.OnDismissListener real = mRef.get();
        if (real != null) {
            real.onDismiss(dialog);
        }
    }

}
