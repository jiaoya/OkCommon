package com.albert.common.recycle;

import android.view.View;

/**
 * recycleview item 点击监听接口
 *
 * @param <H> 数据结构
 */
public interface RecycleItemClickListener<H> {

    /**
     * recycleview item 点击事件回调
     *
     * @param view     点击的view
     * @param position 点击的位置
     * @param data     当前的数据
     */
    void onItemClick(View view, int position, H data);

}
