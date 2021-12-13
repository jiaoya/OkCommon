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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 配合新路由框架 页面传参方式 减少空异常，注意空
    /////////////////////////////// /////////////////////////////////////////////////////////////////

    /**
     * 获取Character
     *
     * @param key
     * @return
     */
    public Character getArgumentsChar(String key) {
        return ParameterSupport.getChar(getArguments(), key);
    }

    /**
     * 获取Character
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Character getArgumentsChar(String key, Character defaultValue) {
        return ParameterSupport.getChar(getArguments(), key, defaultValue);
    }

    /**
     * 获取string
     *
     * @param key
     * @return
     */
    public String getArgumentsString(String key) {
        return ParameterSupport.getString(getArguments(), key);
    }

    /**
     * 获取string
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getArgumentsString(String key, String defaultValue) {
        return ParameterSupport.getString(getArguments(), key, defaultValue);
    }

    /**
     * 获取基本类型Short
     *
     * @param key
     * @return
     */
    public Short getArgumentsShort(String key) {
        return ParameterSupport.getShort(getArguments(), key);
    }

    /**
     * 获取基本类型Short
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Short getArgumentsInt(String key, short defaultValue) {
        return ParameterSupport.getShort(getArguments(), key, defaultValue);
    }

    /**
     * 获取基本数据int
     *
     * @param key
     * @return 没有返回-1
     */
    public Integer getArgumentsInt(String key) {
        return ParameterSupport.getInt(getArguments(), key);
    }


    /**
     * 获取基本数据int
     *
     * @param key
     * @param defaultValue 默认值
     * @return
     */
    public Integer getArgumentsInt(String key, int defaultValue) {
        return ParameterSupport.getInt(getArguments(), key, defaultValue);
    }

    /**
     * 获取长整型
     *
     * @param key
     * @return
     */
    public Long getArgumentsLong(String key) {
        return ParameterSupport.getLong(getArguments(), key);
    }

    /**
     * 获取长整型
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Long getArgumentsLong(String key, long defaultValue) {
        return ParameterSupport.getLong(getArguments(), key, defaultValue);
    }

    /**
     * 获取单精度
     *
     * @param key
     * @return
     */
    public Float getArgumentsFloat(String key) {
        return ParameterSupport.getFloat(getArguments(), key);
    }

    /**
     * 获取单精度
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Float getArgumentsFloat(String key, float defaultValue) {
        return ParameterSupport.getFloat(getArguments(), key, defaultValue);
    }

    /**
     * 获取双精度
     *
     * @param key
     * @return
     */
    public Double getArgumentsDouble(String key) {
        return ParameterSupport.getDouble(getArguments(), key);
    }

    /**
     * 获取双精度
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Double getArgumentsDouble(String key, double defaultValue) {
        return ParameterSupport.getDouble(getArguments(), key, defaultValue);
    }

    /**
     * 获取boolean
     *
     * @param key
     * @return
     */
    public Boolean getArgumentsBoolean(String key) {
        return ParameterSupport.getBoolean(getArguments(), key);
    }

    /**
     * 获取boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Boolean getArgumentsBoolean(String key, boolean defaultValue) {
        return ParameterSupport.getBoolean(getArguments(), key, defaultValue);
    }

    /**
     * 获取序列化的对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Parcelable> T getArgumentsParcelable(String key) {
        return ParameterSupport.getParcelable(getArguments(), key);
    }

    /**
     * 获取序列化的list
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Parcelable> ArrayList<T> getArgumentsParcelableArrayList(String key) {
        return ParameterSupport.getParcelableArrayList(getArguments(), key);
    }

}
