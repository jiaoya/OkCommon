package com.ok.demo.okcore;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.albert.common.http.HttpApiCallback;
import com.albert.common.http.HttpErrorException;
import com.albert.common.http.HttpRxHelp;
import com.albert.okutils.LogUtils;
import com.albert.okutils.OaidUtils;
import com.ok.demo.okcore.databinding.ActivityMainBinding;
import com.ok.demo.okcore.http.ComApi;
import com.ok.demo.okcore.http.Result;
import com.xiaojinzi.component.impl.Router;
import com.albert.common.activity.ComActivity;

public class MainActivity extends ComActivity implements View.OnClickListener {

    private ActivityMainBinding vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        vb.tv1.setOnClickListener(this);
        vb.tv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == vb.tv1) {
            Router.with(this).url("ylt://app/MainActivity2?name=cxj").forward();
        } else if (v == vb.tv2) {
            Router.with(this)
                    .hostAndPath("app/MainActivity3")
                    .putString("name", "cxj")
                    .forward();

            httpFun();
        }

    }


    private void httpFun() {
        HttpRxHelp.subscribe(ComApi.analysis("大阿米芹、"),
                new HttpApiCallback<Result>() {
                    @Override
                    public void onSuc(@NonNull Result result) {
                        LogUtils.e("trere", result.result);
                    }

                    @Override
                    public void onError(@NonNull HttpErrorException e) {

                    }
                });
    }


}