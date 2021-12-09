package com.albert.common.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.albert.common.dialog.weakproxy.WeakDialogListenerHelp;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/12/17.
 *      Desc         :
 * </pre>
 */
class OkWeakDialog extends Dialog {

    public OkWeakDialog(@NonNull Context context) {
        super(context);
    }

    public OkWeakDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected OkWeakDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setOnCancelListener(@Nullable OnCancelListener listener) {
        super.setOnCancelListener(WeakDialogListenerHelp.proxy(listener));
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(WeakDialogListenerHelp.proxy(listener));
    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(WeakDialogListenerHelp.proxy(listener));
    }

}
