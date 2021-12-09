package com.albert.common.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.albert.okutils.ActivityUtils;
import com.albert.okutils.KeyboardUtils;
import com.xiaojinzi.component.support.ParameterSupport;

import java.util.ArrayList;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-10.
 *      Desc         : 核心或基本Fragment，封住一些基本操作和工具
 * </pre>
 */
public abstract class ComFragment extends Fragment implements View.OnClickListener {

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = getLayoutView(inflater, container);
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        initData();
        if (rootView != null) {
            return rootView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
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
     * 获取布局文件
     *
     * @return
     */
    protected Integer getLayoutId() {
        return null;
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        rootView = null;
        super.onDestroyView();
        if (getActivity() != null) {
            KeyboardUtils.hideSoftInput(getActivity());
        }
    }

    /**
     * 调用 findViewById(id)
     *
     * @param id
     */
    public final <T extends View> T findViewById(@IdRes int id) {
        if (rootView == null) throw new NullPointerException("rootView 为空");
        T view = rootView.findViewById(id);
        if (null == view) throw new NullPointerException("Id 不正确");
        return view;
    }

    /**
     * 设置view的点击事件
     *
     * @param views
     */
    public void setOnClickListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 关闭页面
     */
    public void closePage() {
        if (getActivity() != null && !ActivityUtils.isDestroy(getActivity())) {
            getActivity().finish();
        }
    }
    /*---------------------------获取页面传参----------------------------------*/

    /**
     * 页面传参 根据key获取string
     *
     * @param key
     * @return
     */
    public String getArgumentString(String key) {
        return getArgumentString(key, "");
    }

    /**
     * 页面传参 根据key获取string
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getArgumentString(String key, String defaultValue) {
        return ParameterSupport.getString(getArguments(), key, defaultValue);
    }

    public Boolean getArgumentBoolean(String key) {
        return getArgumentBoolean(key, false);
    }

    /**
     * 获取页面传值 Boolean 类型
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Boolean getArgumentBoolean(String key, boolean defaultValue) {
        return ParameterSupport.getBoolean(getArguments(), key, defaultValue);
    }

    public Integer getArgumentInt(String key) {
        return getArgumentInt(key, -1);
    }

    /**
     * 获取页面传值 int 类型
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Integer getArgumentInt(String key, int defaultValue) {
        return ParameterSupport.getInt(getArguments(), key, defaultValue);
    }

    public Long getArgumentLong(String key) {
        return getArgumentLong(key, -1);
    }

    /**
     * 获取页面传值 Long 类型
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Long getArgumentLong(String key, long defaultValue) {
        return ParameterSupport.getLong(getArguments(), key, defaultValue);
    }

    public Float getArgumentFloat(String key) {
        return getArgumentFloat(key, -1);
    }

    /**
     * 获取页面传值 float类型
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Float getArgumentFloat(String key, float defaultValue) {
        return ParameterSupport.getFloat(getArguments(), key, defaultValue);
    }

    public Double getArgumentDouble(String key) {
        return getArgumentDouble(key, -1);
    }

    /**
     * 获取页面传值 Double 类型
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Double getArgumentDouble(String key, double defaultValue) {
        return ParameterSupport.getDouble(getArguments(), key, defaultValue);
    }

    /**
     * 获取页面传值 Parcelable 类型
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Parcelable> T getArgumentParcelable(String key) {
        return ParameterSupport.getParcelable(getArguments(), key);
    }

    /**
     * 获取页面传值 ParcelableArrayList 类型
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Parcelable> ArrayList<T> getArgumentParcelableArrayList(String key) {
        return ParameterSupport.getParcelableArrayList(getArguments(), key);
    }


}
