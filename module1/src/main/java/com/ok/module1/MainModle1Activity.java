package com.ok.module1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xiaojinzi.component.anno.RouterAnno;

@RouterAnno(path = "MainModle1Activity")
public class MainModle1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmodle1);

    }


}