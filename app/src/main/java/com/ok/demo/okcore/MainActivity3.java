package com.ok.demo.okcore;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.albert.common.activity.ComActivity;
import com.ok.demo.okcore.databinding.ActivityMain3Binding;
import com.xiaojinzi.component.anno.RouterAnno;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2021.
 *      Author       : jiaoya.
 *      Created Time : 12/13/21.
 *      Desc         :
 * </pre>
 */
@RouterAnno(hostAndPath = "app/MainActivity3")
public class MainActivity3 extends ComActivity {

    ActivityMain3Binding vb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        setFfragment(BlankFragment.newInstance(getIntent().getExtras()));
    }


    public void setFfragment(@Nullable Fragment fragment) {
        addFragment(R.id.flContnet, fragment);
    }

    /**
     * 添加fragment
     *
     * @param id
     * @param fragment
     */
    public void addFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager()    //
                .beginTransaction()
                // 此处的R.id.fragment_container是要盛放fragment的父容器
                .add(id, fragment)
                .commitAllowingStateLoss();
    }
}