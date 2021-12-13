package com.ok.demo.okcore;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.albert.common.activity.ComActivity;
import com.albert.okutils.NetworkUtils;
import com.ok.demo.okcore.databinding.ActivityMain2Binding;
import com.xiaojinzi.component.anno.AttrValueAutowiredAnno;
import com.xiaojinzi.component.anno.RouterAnno;
import com.xiaojinzi.component.support.ParameterSupport;

import java.util.Set;


@RouterAnno(hostAndPath = "app/MainActivity2")
public class MainActivity2 extends ComActivity implements NetworkUtils.NetworkListener {

    ActivityMain2Binding vb;

//    @AttrValueAutowiredAnno("name")
//    String valueString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);
        vb = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

//        Bundle bundle = getBundleExtra();
//
//        String c = bundle.getString("name");
//        Uri uri = ParameterSupport.getUri(getIntent());
//        Set<String> pke = uri.getQueryParameterNames();
//
        String a = getStringExtra("name", "ssss");
        String b = ParameterSupport.getQueryString(getIntent(), "name");

        setOnClickListener(vb.tv1, vb.tv2, vb.tv3, vb.tv4, vb.tv5);
    }

    public Bundle getBundleExtra() {
        if (getIntent() == null) {
            return null;
        }

        Uri uri = ParameterSupport.getUri(getIntent());
        if (uri == null) {
            return null;
        }
        Bundle bundle = null;
        Set<String> keys = uri.getQueryParameterNames();
        if (keys.size() > 0) {
            bundle = new Bundle();
            for (String key : keys) {
                bundle.putString(key, uri.getQueryParameter(key));
            }
        }
        if (bundle != null) {
            return bundle;
        }

        return getIntent().getExtras();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // String a = valueString;
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