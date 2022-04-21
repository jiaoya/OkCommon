package com.albert.common.recycle;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.CoroutineDispatcher;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2022.
 *      Author       : jiaoya.
 *      Created Time : 2022/3/29.
 *      Desc         : 与Paging结合使用
 * </pre>
 */
public abstract class ComPagingDataAdapter<D, VH extends ComHolder<D>> extends PagingDataAdapter<D, VH> {

    public Activity mActivity;
    public Fragment mFragment;
    public RecycleItemClickListener<D> mRvItemClickListener;

    public ComPagingDataAdapter(Fragment fragment,
                                @NonNull DiffUtil.ItemCallback<D> diffCallback) {
        super(diffCallback);
        mFragment = fragment;
    }

    public ComPagingDataAdapter(Activity activity,
                                @NonNull DiffUtil.ItemCallback<D> diffCallback) {
        super(diffCallback);
        mActivity = activity;
    }

    public ComPagingDataAdapter(Activity activity, Fragment fragment,
                                @NonNull DiffUtil.ItemCallback<D> diffCallback) {
        super(diffCallback);
        mActivity = activity;
        mFragment = fragment;
    }

    public ComPagingDataAdapter(Activity activity, Fragment fragment,
                                @NonNull DiffUtil.ItemCallback<D> diffCallback,
                                @NonNull CoroutineDispatcher mainDispatcher) {
        super(diffCallback, mainDispatcher);
        mActivity = activity;
        mFragment = fragment;
    }

    public ComPagingDataAdapter(Activity activity, Fragment fragment,
                                @NonNull DiffUtil.ItemCallback<D> diffCallback,
                                @NonNull CoroutineDispatcher mainDispatcher,
                                @NonNull CoroutineDispatcher workerDispatcher) {
        super(diffCallback, mainDispatcher, workerDispatcher);
        mActivity = activity;
        mFragment = fragment;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBindViewHolder(getItem(position), position, mRvItemClickListener);
    }

    /**
     * 设置监听
     */
    public void setRvItemClickListener(RecycleItemClickListener<D> rvItemClickListener) {
        this.mRvItemClickListener = rvItemClickListener;
    }

}