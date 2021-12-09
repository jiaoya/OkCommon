package com.albert.common.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.albert.common.R;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/4/15.
 *      Desc         : 默认无边框、无标题、背景颜色透明、底部弹出动画
 *                     背景高度MATCH_PARENT，宽度MATCH_PARENT
 * </pre>
 */
public abstract class ComDailogFragment extends OkDialogFragment {

    protected String TAG = getClass().getSimpleName();
    protected Window mWindow;
    protected ViewGroup rootView;
    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mWindow = getDialog().getWindow();
        mContext = getContext();
        rootView = (ViewGroup) inflater.inflate(R.layout.core_dialog_layout, container, false);
        View view = getLayoutView(inflater, rootView);
        if (view != null) {
            rootView.addView(view);
        } else {
            rootView.addView(inflater.inflate(getLayoutResId(), rootView, false));
        }
        rootView.setOnClickListener(v -> {
            if (setIsBackClickDismiss()) {
                dismiss();
            }
        });
        initData(rootView);
        return rootView;
    }

    /**
     * params.dimAmount = 0.0f; 外部窗口透明
     */
    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        setParams(params);
        mWindow.setAttributes(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 设置弹窗的显示风格 默认 DialogFragment.STYLE_NORMAL=0
     */
    public int setDialogStytle() {
        return DialogFragment.STYLE_NO_TITLE;
    }

    /**
     * 设置主题
     *
     * @return
     */
    protected int setDialogTheme() {
        return -1;
    }

    /**
     * 这里处理viewbing
     *
     * @param inflater
     * @param container
     * @return
     */
    protected View getLayoutView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return null;
    }

    /**
     * 布局文件
     *
     * @return LayoutId
     */
    protected int getLayoutResId() {
        return 0;
    }

    /**
     * 数据和视图的处理
     *
     * @param view
     */
    protected abstract void initData(View view);

    /**
     * 给dialogFragment设置位置参数 弹出大小、中间、地步等等
     *
     * @param params
     */
    protected abstract void setParams(WindowManager.LayoutParams params);


    /**
     * 设置背景透明,必须在onstart之后调用
     */
    public void setBgTransprent() {
        if (null != mWindow && null != rootView) {
            WindowManager.LayoutParams params = mWindow.getAttributes();
            params.dimAmount = 0.0f;
            mWindow.setAttributes(params);
            rootView.setBackgroundResource(R.color.transparent);
        }
    }

    /**
     * 点击背景是否消失，如果 params.height/width = WindowManager.LayoutParams.WRAP_CONTENT 失效;
     *
     * @return
     */
    public boolean setIsBackClickDismiss() {
        return true;
    }

    /**
     * 设置背景，以布局文件的根View的BacKGround为主，这个方法也是设置根View的Background
     *
     * @param drawable
     */
    public void setDialogBackground(@NonNull Drawable drawable) {
        mWindow.setBackgroundDrawable(drawable);
    }

    /**
     * 设置背景，以布局文件的根View的BacKGround为主，这个方法也是设置根View的Background
     *
     * @param resId
     */
    public void setDialogBackgroundResource(@DrawableRes int resId) {
        mWindow.setBackgroundDrawableResource(resId);
    }

    @Override
    public void show(@NonNull FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.addToBackStack(null);
        super.show(manager, tag);
    }

    public void show(@NonNull FragmentManager manager) {
        try {
            show(manager, String.valueOf(System.currentTimeMillis()));
        } catch (Exception e) {
        }
    }

}
