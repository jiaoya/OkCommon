package com.albert.common.http.log;

import android.view.View;

import com.albert.common.R;
import com.albert.common.databinding.CoreRecycleItemHttpLogBinding;
import com.albert.common.recycle.ComHolder;


/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/9/16.
 *      Desc         :
 * </pre>
 */
public class HttpLogHolder extends ComHolder<HttpLogEnty> implements View.OnClickListener {

    public CoreRecycleItemHttpLogBinding vb;

    public HttpLogHolder(CoreRecycleItemHttpLogBinding viewBinding) {
        super(viewBinding);
        vb = viewBinding;
        vb.tvApiName.setOnClickListener(this);
        vb.tvContent.setOnClickListener(this);
    }

    @Override
    public void onBindViewHolder(int position, HttpLogEnty dataBean) {
        if (dataBean.path != null) {
            vb.tvApiName.setText(dataBean.path);
        }
        if (dataBean.content != null) {
            vb.tvContent.setText(dataBean.content);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvApiName) {
            showHiddin();
        } else if (id == R.id.tvContent) {
            showHiddin();
        }
    }

    private void showHiddin() {
        if (vb.tvContent.getVisibility() == View.VISIBLE) {
            vb.tvContent.setVisibility(View.GONE);
        } else {
            vb.tvContent.setVisibility(View.VISIBLE);
        }
    }


}
