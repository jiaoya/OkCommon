package com.albert.common.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.albert.okutils.KeyboardUtils;
import com.xiaojinzi.component.support.ParameterSupport;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-10.
 *      Desc         : 核心或最基本activity，封住一些基本操作和工具
 * </pre>
 */
public abstract class ComActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 当此Activity启动别的Activity时，屏蔽当前Activity的触屏事件，防止多次跳转
     */
    private boolean blockTouchEventWhenNewActivityIsStarting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutView(LayoutInflater.from(this));
        if (view != null) {
            setContentView(view);
        }
    }

    /**
     * 这里处理viewbing
     *
     * @param inflater
     * @return
     */
    protected View getLayoutView(LayoutInflater inflater) {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        blockTouchEventWhenNewActivityIsStarting = false;
    }

    @Override
    public void onClick(View v) {

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

    /**
     * 点击软键盘外面的区域关闭软键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                KeyboardUtils.hideSoftInput(this);
            }
        }
        return blockTouchEventWhenNewActivityIsStarting || super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 设置输入焦点靠右
     */
    public static void changeFocusToRight(EditText editText) {
        //设置输入焦点靠右
        String str = editText.getText().toString();
        try {
            if (!TextUtils.isEmpty(str) && str.length() > 0) {
                editText.setSelection(str.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public Character getCharExtra(String key) {
        return ParameterSupport.getChar(getIntent(), key);
    }

    /**
     * 获取Character
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Character getCharExtra(String key, Character defaultValue) {
        return ParameterSupport.getChar(getIntent(), key, defaultValue);
    }

    /**
     * 获取页面传参string值
     *
     * @param key
     * @return
     */
    public String getStringExtra(String key) {
        return getStringExtra(key, "");
    }

    /**
     * 获取页面传参string值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getStringExtra(String key, String defaultValue) {
        return ParameterSupport.getString(getIntent(), key, defaultValue);
    }

    /**
     * 获取页面传参 boolean
     *
     * @param key
     * @return
     */
    public Boolean getBooleanExtra(String key) {
        return ParameterSupport.getBoolean(getIntent(), key);
    }

    /**
     * 获取页面传参 boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Boolean getBooleanExtra(String key, boolean defaultValue) {
        return ParameterSupport.getBoolean(getIntent(), key, defaultValue);
    }

    /**
     * 获取页面传参Integer
     *
     * @param key
     * @return
     */
    public Integer getIntExtra(String key) {
        return ParameterSupport.getInt(getIntent(), key);
    }

    /**
     * 获取页面传参int
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Integer getIntExtra(String key, int defaultValue) {
        return ParameterSupport.getInt(getIntent(), key, defaultValue);
    }

    /**
     * 获取页面传参long
     *
     * @param key
     * @return
     */
    public Long getLongExtra(String key) {
        return ParameterSupport.getLong(getIntent(), key);
    }

    /**
     * 获取页面传参long
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Long getLongExtra(String key, long defaultValue) {
        return ParameterSupport.getLong(getIntent(), key, defaultValue);
    }

    /**
     * 获取页面传参short
     *
     * @param key
     * @return
     */
    public Short getShortExtra(String key) {
        return ParameterSupport.getShort(getIntent(), key);
    }

    /**
     * 获取页面传参short
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Short getShortExtra(String key, short defaultValue) {
        return ParameterSupport.getShort(getIntent(), key, defaultValue);
    }

    /**
     * 获取页面传参float
     *
     * @param key
     * @return
     */
    public Float getFloatExtra(String key) {
        return ParameterSupport.getFloat(getIntent(), key);
    }

    /**
     * 获取页面传参float
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Float getFloatExtra(String key, float defaultValue) {
        return ParameterSupport.getFloat(getIntent(), key, defaultValue);
    }

    /**
     * 获取页面传参double
     *
     * @param key
     * @return
     */
    public Double getDoubleExtra(String key) {
        return ParameterSupport.getDouble(getIntent(), key);
    }

    /**
     * 获取页面传参double
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Double getDoubleExtra(String key, double defaultValue) {
        return ParameterSupport.getDouble(getIntent(), key, defaultValue);
    }

    /**
     * 获取序列化的值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Parcelable> T getParcelableExtra(String key) {
        return ParameterSupport.getParcelable(getIntent(), key);
    }

    /**
     * 获取序列化
     *
     * @param key
     * @return
     */
    public Parcelable[] getParcelableArrayExtra(String key) {
        return ParameterSupport.getParcelableArray(getIntent(), key);
    }

    /**
     * 获取序列化值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String key) {
        return ParameterSupport.getParcelableArrayList(getIntent(), key);
    }

    /**
     * 序列化的值
     *
     * @param key
     * @return
     */
    public Serializable getSerializableExtra(String key) {
        return ParameterSupport.getSerializable(getIntent(), key);
    }
}
