package com.ok.demo.okcore.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.albert.common.dialog.ComDialog;
import com.ok.demo.okcore.R;
import com.ok.demo.okcore.databinding.DialogTestBinding;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2022.
 *      Author       : jiaoya.
 *      Created Time : 2022/1/18.
 *      Desc         :
 * </pre>
 */
public class DialgoTest extends ComDialog {

    private DialogTestBinding vb;

    public DialgoTest(@NonNull Context context) {
        super(context, R.style.BaseDialog);
    }

//    @Override
//    protected View getLayoutView(LayoutInflater layoutInflater) {
//        vb = DialogTestBinding.inflate(layoutInflater);
//        return vb.getRoot();
//    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_test;
    }

    @Override
    protected void initData() {

    }


}