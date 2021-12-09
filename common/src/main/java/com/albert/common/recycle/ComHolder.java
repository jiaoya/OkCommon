package com.albert.common.recycle;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.List;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/4/16.
 *      Desc         : 基础holder
 * </pre>
 */
public class ComHolder<H> extends RecyclerView.ViewHolder implements View.OnClickListener {

    public Activity mActivity;
    public Fragment mFragment;
    public RecycleItemClickListener<H> mRecycleItemClickListener;
    public H mData;
    public int mPostion;

    public ComHolder(ViewBinding viewBinding) {
        this(null, null, viewBinding);
    }

    public ComHolder(Activity activity, ViewBinding viewBinding) {
        this(activity, null, viewBinding);
    }

    public ComHolder(Fragment fragment, ViewBinding viewBinding) {
        this(null, fragment, viewBinding);
    }

    /**
     * ViewBinding 初始化
     *
     * @param activity
     * @param fragment
     * @param viewBinding
     */
    public ComHolder(Activity activity, Fragment fragment, ViewBinding viewBinding) {
        this(viewBinding.getRoot());
        if (activity != null) {
            mActivity = activity;
        } else if (itemView.getContext() instanceof Activity) {
            mActivity = (Activity) itemView.getContext();
        }
        mFragment = fragment;
    }

    public ComHolder(@NonNull View itemView) {
        this(null, null, itemView);
    }

    /**
     * view 初始化
     *
     * @param activity
     * @param fragment
     * @param view
     */
    public ComHolder(Activity activity, Fragment fragment, @NonNull View view) {
        super(view);
        if (activity != null) {
            mActivity = activity;
        } else if (itemView.getContext() instanceof Activity) {
            mActivity = (Activity) itemView.getContext();
        }
        mFragment = fragment;
    }

    public ComHolder(ViewGroup viewGroup, @LayoutRes int resource) {
        this(null, null, viewGroup, resource);
    }

    /**
     * xml 布局文件初始化
     *
     * @param activity
     * @param fragment
     * @param viewGroup
     * @param resource
     */
    public ComHolder(Activity activity, Fragment fragment, ViewGroup viewGroup, @LayoutRes int resource) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false));
        if (activity != null) {
            mActivity = activity;
        } else if (viewGroup.getContext() instanceof Activity) {
            mActivity = (Activity) viewGroup.getContext();
        }
        mFragment = fragment;
    }

    public void onBindViewHolder(int position, H data) {
        onBindViewHolder(null, position, data, null);
    }

    /**
     * 设置绑定ui数据，这里最好不要做ui相关的初始化操作
     *
     * @param allDatas
     * @param position            当前的位置
     * @param data                当前的数据
     * @param rvItemClickListener item点击回调
     */
    public void onBindViewHolder(List<H> allDatas, int position, H data, RecycleItemClickListener<H> rvItemClickListener) {
        mPostion = position;
        mData = data;
        mRecycleItemClickListener = rvItemClickListener;
    }

    /**
     * 调用 findViewById(id)
     *
     * @param id
     */
    public final <T extends View> T findViewById(@IdRes int id) {
        T view = itemView.findViewById(id);
        if (null == view) throw new IllegalArgumentException("Id 不正确");
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


}
