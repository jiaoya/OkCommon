package com.albert.common.http.log;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.albert.common.R;
import com.albert.common.databinding.CoreDialogHttpLogBinding;
import com.albert.common.databinding.CoreRecycleItemHttpLogBinding;
import com.albert.common.dialog.ComDialog;
import com.albert.common.http.HttpLogging;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2020.
 *      Author       : jiaoya.
 *      Created Time : 2020/9/16.
 *      Desc         : 日志弹框
 * </pre>
 */
public class HttpLogDialog extends ComDialog implements View.OnClickListener {

    CoreDialogHttpLogBinding vbing;
    HttpLogAdapter mAdapter;

    public static HttpLogDialog newInstance(Context context) {
        return new HttpLogDialog(context);
    }

    public HttpLogDialog(@NonNull Context context) {
        super(context, R.style.ComDialog);
    }

    @Override
    protected View getLayoutView(LayoutInflater layoutInflater) {
        vbing = CoreDialogHttpLogBinding.inflate(layoutInflater);
        return vbing.getRoot();
    }

    @Override
    protected void initData() {
        vbing.tvClearLog.setOnClickListener(this);
        vbing.tvRefreshLog.setOnClickListener(this);
        vbing.tvBack.setOnClickListener(this);
        vbing.rlvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HttpLogAdapter();
        vbing.rlvContent.setAdapter(mAdapter);
    }

    @Override
    protected void setParams(LayoutParams params) {
        super.setParams(params);
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        mAdapter.setmData(HttpLogging.getLogDatas());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvBack) {
            dismiss();
        } else if (id == R.id.tvClearLog) {
            HttpLogging.clearLog();
            mAdapter.clear();
        } else if (id == R.id.tvRefreshLog) {
            mAdapter.setmData(HttpLogging.getLogDatas());
        }
    }

    public static class HttpLogAdapter extends RecyclerView.Adapter<HttpLogHolder> {

        private List<HttpLogEnty> mData = new ArrayList<>();

        @NonNull
        @Override
        public HttpLogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new HttpLogHolder(CoreRecycleItemHttpLogBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HttpLogHolder holder, int position) {
            holder.onBindViewHolder(position, mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void setmData(List<HttpLogEnty> data) {
            Observable.just(data)
                    .delay(500, TimeUnit.MILLISECONDS)
                    .doOnNext(httpLogEnties -> {
                        mData.clear();
                        mData.addAll(httpLogEnties);
                    })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<HttpLogEnty>>() {
                        @Override
                        public void accept(List<HttpLogEnty> httpLogEnties) {
                            notifyDataSetChanged();
                        }
                    });
        }

        public void clear() {
            mData.clear();
            notifyDataSetChanged();
        }
    }
}
