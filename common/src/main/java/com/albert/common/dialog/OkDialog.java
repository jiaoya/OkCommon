package com.albert.common.dialog;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/12/17.
 *      Desc         :
 * </pre>
 */
public class OkDialog extends OkRefkDialog {

    public OkDialog(@NonNull Context context) {
        super(context);
    }

    public OkDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected OkDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


}
