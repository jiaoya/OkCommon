package com.albert.common.dialog.weakproxy;

import android.content.DialogInterface;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/12/17.
 *      Desc         : 软引用，防止泄露
 * </pre>
 */
public class RefDialogListenerHelp {

    public static RefOnCancelListener proxy(DialogInterface.OnCancelListener real) {
        return new RefOnCancelListener(real);
    }

    public static RefOnDismissListener proxy(DialogInterface.OnDismissListener real) {
        return new RefOnDismissListener(real);
    }

    public static RefOnShowListener proxy(DialogInterface.OnShowListener real) {
        return new RefOnShowListener(real);
    }
}
