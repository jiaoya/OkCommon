package com.albert.common.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.albert.common.dialog.weakproxy.RefDialogListenerHelp;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/12/17.
 *      Desc         :
 * </pre>
 */
class OkRefkDialog extends Dialog {

    public OkRefkDialog(@NonNull Context context) {
        super(context);
    }

    public OkRefkDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected OkRefkDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setOnCancelListener(@Nullable OnCancelListener listener) {
        super.setOnCancelListener(RefDialogListenerHelp.proxy(listener));
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(RefDialogListenerHelp.proxy(listener));
    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(RefDialogListenerHelp.proxy(listener));
    }

}
