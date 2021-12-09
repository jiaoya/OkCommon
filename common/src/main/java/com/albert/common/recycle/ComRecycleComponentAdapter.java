package com.albert.common.recycle;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/7/30.
 *      Desc         : RecyclerView 页面组合，根据type区分
 * </pre>
 */
public class ComRecycleComponentAdapter extends RecyclerView.Adapter {

    public Activity mActivity;
    public Fragment mFragment;
    public List<RvItemBean> mDatas = new ArrayList<>();
    public RecycleItemClickListener mRvItemClickListener;

    public ComRecycleComponentAdapter() {
    }

    public ComRecycleComponentAdapter(Activity activity, Fragment fragment) {
        mActivity = activity;
        mFragment = fragment;
    }

    public ComRecycleComponentAdapter(Activity activity) {
        mActivity = activity;
    }

    public ComRecycleComponentAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (position < mDatas.size()) {
            return mDatas.get(position).type;
        } else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(List<RvItemBean> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    /**
     * 设置数据不刷新
     *
     * @param data
     */
    public void setDataNoNotify(List<RvItemBean> data) {
        this.mDatas = data;
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(RvItemBean data) {
        int currentSize = mDatas.size() == 0 ? 1 : mDatas.size();
        this.mDatas.add(data);
        notifyItemRangeChanged(currentSize - 1, 1, false);
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<RvItemBean> data) {
        int currentSize = mDatas.size() == 0 ? 1 : mDatas.size();
        this.mDatas.addAll(data);
        notifyItemRangeChanged(currentSize - 1, data.size(), false);
    }

    /**
     * 获取页面数据
     *
     * @return
     */
    public List<RvItemBean> getData() {
        return mDatas;
    }

    /**
     * 获取对应位置的数据
     *
     * @param position
     * @return
     */
    public Object getItemData(int position) {
        return mDatas.get(position).itemData;
    }

    /**
     * 清理所有
     */
    public void clearAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置监听
     */
    public void setRecycleItemClickListener(RecycleItemClickListener rvItemClickListener) {
        this.mRvItemClickListener = rvItemClickListener;
    }

    /**
     * 获取 Inflater
     *
     * @param view
     * @return
     */
    public LayoutInflater getInflater(View view) {
        return LayoutInflater.from(view.getContext());
    }


}
