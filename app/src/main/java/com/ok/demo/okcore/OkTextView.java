package com.ok.demo.okcore;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2022.
 *      Author       : jiaoya.
 *      Created Time : 2022/1/14.
 *      Desc         :
 * </pre>
 */
public class OkTextView extends AppCompatTextView {

    public OkTextView(@NonNull Context context) {
        super(context);
    }

    public OkTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OkTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }
}