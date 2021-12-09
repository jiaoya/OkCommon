package com.albert.common.recycle;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-11-18.
 *      Desc         : 每个组件页结合 ComRecycleComponentAdapter  每个item的标准数据模型。每个组件页以List<PageItemBean> 组装数据
 * </pre>
 */
public class RvItemBean {

    /**
     * 类型 recycle item的类型
     */
    public int type;
    /**
     * viewholder类型
     */
    public Class holderType;
    /**
     * 数据类型
     */
    public Class dataType;
    /**
     * item 数据
     */
    public Object itemData;

}
