package com.ok.demo.okcore;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 10/29/21.
 *      Desc         :
 * </pre>
 */
public class MyDiffCallback extends DiffUtil.Callback {


    /**
     * 旧数据的size
     */
    @Override
    public int getOldListSize() {
        return 0;
    }

    /**
     * 新数据的size
     */
    @Override
    public int getNewListSize() {
        return 0;
    }

    /**
     * 这个方法自由定制 ，
     * 在对比数据的时候会被调用
     * 返回 true 被判断为同一个item
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    /**
     * 在上面的方法返回true 时，
     * 这个方法才会被diff 调用
     * 返回true 就证明内容相同
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

}