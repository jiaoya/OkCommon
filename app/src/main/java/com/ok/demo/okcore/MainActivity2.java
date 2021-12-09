package com.ok.demo.okcore;

import android.os.Bundle;
import android.view.View;

import com.albert.common.activity.ComActivity;
import com.albert.okutils.NetworkUtils;
import com.ok.demo.okcore.databinding.ActivityMain2Binding;
import com.xiaojinzi.component.anno.AttrValueAutowiredAnno;
import com.xiaojinzi.component.anno.RouterAnno;
import com.xiaojinzi.component.support.ParameterSupport;


@RouterAnno(path = "MainAcivity2")
public class MainActivity2 extends ComActivity implements NetworkUtils.NetworkListener {

    ActivityMain2Binding vb;

    @AttrValueAutowiredAnno("name")
    String valueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);
        vb = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        String a = getStringExtra("name");
        String b = ParameterSupport.getString(getIntent(), "name");

        setOnClickListener(vb.tv1, vb.tv2, vb.tv3, vb.tv4, vb.tv5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String a = valueString;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegstNet();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == vb.tv1) {
            regstNet();
        } else if (v == vb.tv2) {
            NetworkUtils.isConnected();
        }
    }

    private void regstNet() {
        NetworkUtils.registerNetworkListener(this);
    }

    private void unRegstNet() {
        NetworkUtils.unregisterNetworkListener(this);
    }

    @Override
    public void onConnect(boolean isConnect) {

    }

    @Override
    public void onMobileNet() {

    }

    @Override
    public void onWifiNet() {

    }
}