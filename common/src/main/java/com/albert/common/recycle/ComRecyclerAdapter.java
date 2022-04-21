package com.albert.common.recycle;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.albert.okutils.NullUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/7/20.
 *      Desc         : 封装app内通用的属性
 * </pre>
 */
public abstract class ComRecyclerAdapter<D, VH extends ComHolder<D>> extends RecyclerView.Adapter<VH> {

    public Activity mActivity;
    public Fragment mFragment;
    public List<D> mDatas = new ArrayList<>();
    public RecycleItemClickListener<D> mRvItemClickListener;

    public ComRecyclerAdapter() {
    }

    public ComRecyclerAdapter(Activity activity) {
        mActivity = activity;
    }

    public ComRecyclerAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    public ComRecyclerAdapter(List<D> mDatas) {
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public abstract VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBindViewHolder(mDatas, mDatas.get(position), position, mRvItemClickListener);
    }

    @Override
    public int getItemCount() {
        if (NullUtil.notEmpty(mDatas)) {
            return mDatas.size();
        } else {
            return 0;
        }
    }

    /**
     * 获取LayoutInflater
     *
     * @param view
     * @return
     */
    public LayoutInflater getInflater(View view) {
        return LayoutInflater.from(view.getContext());
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(List<D> data) {
        this.mDatas = data;
        notifyItemRangeChanged(0, mDatas.size());
    }

    /**
     * 设置数据不刷新
     *
     * @param data
     */
    public void setDataNotNotify(List<D> data) {
        this.mDatas = data;
    }

    /**
     * 添加数据并刷新
     *
     * @param data
     */
    public void addData(D data) {
        if (data != null) {
            this.mDatas.add(data);
            notifyItemRangeInserted(mDatas.size() - 1, mDatas.size());
        }
    }

    /**
     * 添加数据并刷新
     *
     * @param data
     */
    public void addData(List<D> data) {
        if (NullUtil.notEmpty(data)) {
            this.mDatas.addAll(data);
            notifyItemRangeInserted(mDatas.size() - data.size(), mDatas.size());
        }
    }

    /**
     * 添加数据不刷新
     *
     * @param data
     */
    public void addDataNotNotify(D data) {
        if (data != null) {
            this.mDatas.add(data);
        }
    }

    /**
     * 添加数据不刷新
     *
     * @param data
     */
    public void addDataNotNotify(List<D> data) {
        if (NullUtil.notEmpty(data)) {
            this.mDatas.addAll(data);
        }
    }

    /**
     * 清除
     */
    public void clear() {
        if (NullUtil.notEmpty(mDatas)) {
            mDatas.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 获取数据
     *
     * @return
     */
    public List<D> getData() {
        return mDatas;
    }

    /**
     * 设置监听
     */
    public void setRvItemClickListener(RecycleItemClickListener<D> rvItemClickListener) {
        this.mRvItemClickListener = rvItemClickListener;
    }
}
