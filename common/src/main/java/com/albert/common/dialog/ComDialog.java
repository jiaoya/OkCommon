package com.albert.common.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/4/15.
 *      Desc         :
 * </pre>
 */
public abstract class ComDialog extends OkDialog {

    /**
     * 点击外部是否允许消失
     */
    private boolean isOutDismiss = true;
    /**
     * /显示的位置
     */
    private int mGravity = Gravity.BOTTOM;
    /**
     * 外部是否透明
     */
    private boolean mIsTransparency = false;
    /**
     * 点击系统返回是否消失
     */
    private boolean mIsBackDismiss = true;


    public ComDialog(@NonNull Context context) {
        super(context);
    }

    public ComDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ComDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutView(getLayoutInflater());
        if (view != null) {
            setContentView(view);
        } else {
            setContentView(getLayoutResId());
        }
        setCancelable(isOutDismiss);
        setCanceledOnTouchOutside(isOutDismiss);
        initData();
    }

    /**
     * 这里处理viewbing
     *
     * @return
     */
    protected View getLayoutView(LayoutInflater layoutInflater) {
        return null;
    }

    /**
     * 布局文件
     *
     * @return LayoutId
     */
    @Deprecated
    protected int getLayoutResId() {
        return 0;
    }

    /**
     * 数据和视图的处理
     */
    protected abstract void initData();

    @Override
    protected void onStart() {
        super.onStart();
        if (getWindow() != null) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            // dialog 如果宽高都用 MATCH_PARENT，点击空白区域则无法消失，  dialog.setCancelable(true);将无效
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            setParams(params);
            getWindow().setAttributes(params);
            getWindow().setGravity(mGravity);

            // 设置纯透明
            if (mIsTransparency) {
                getWindow().setDimAmount(0);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!mIsBackDismiss) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 给dialogFragment设置位置参数 弹出大小、中间、地步等等
     * 横屏： params.width = LayoutParams.WRAP_CONTENT;params.height = LayoutParams.MATCH_PARENT;
     *
     * @param params
     */
    protected void setParams(WindowManager.LayoutParams params) {

    }

    /**
     * 设置弹出的位置，默认底部，如果布局文件的根布局是 MATCH_PARENT，则无效
     *
     * @param mGravity
     * @return
     * @see Gravity
     */
    public ComDialog setGravity(int mGravity) {
        this.mGravity = mGravity;
        return this;
    }

    /**
     * 设置背景透明，默认半透明，如果布局文件的根布局是 MATCH_PARENT，并且有背景颜色则无效
     *
     * @param transparency
     * @return
     */
    public ComDialog setTransparency(boolean transparency) {
        this.mIsTransparency = transparency;
        if (getWindow() != null) {
            getWindow().setDimAmount(0);
        }
        return this;
    }

    /**
     * 点击系统返回按钮是否消失
     *
     * @param isDissmiss
     * @return
     */
    public ComDialog setBackDissmiss(boolean isDissmiss) {
        this.mIsBackDismiss = isDissmiss;
        return this;
    }

    /**
     * 点击外部是否允许消失，如果布局文件的根布局是 MATCH_PARENT，并且有背景颜色则无效
     *
     * @param isDissmiss
     * @return
     */
    public ComDialog setOutDissmiss(boolean isDissmiss) {
        this.isOutDismiss = isDissmiss;
        return this;
    }

    /**
     * 显示
     *
     * @return
     */
    public ComDialog showDialog() {
        this.show();
        return this;
    }


}
