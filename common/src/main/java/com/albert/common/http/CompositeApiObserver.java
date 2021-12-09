package com.albert.common.http;

import androidx.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2018.
 *      Author       : jiaoya.
 *      Created Time : 2018/11/9.
 *      Desc         : 统一管理rxjava Disposable
 * </pre>
 */
public class CompositeApiObserver {

    CompositeDisposable mDisposable;

    public CompositeApiObserver() {
        mDisposable = new CompositeDisposable();
    }

    /**
     * 添加到管理里
     *
     * @param disposable
     */
    public void add(@NonNull Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        if (!disposable.isDisposed()) {
            mDisposable.add(disposable);
        }
    }

    public void remove(@NonNull Disposable disposable) {
        if (!disposable.isDisposed() && mDisposable != null) {
            mDisposable.remove(disposable);
        }
    }

    /**
     * 移除其他的观察者，然后在调用{@link #clear()}，这样在界面消失后仍然能接收到回调
     *
     * @param disposable 此观察者不移除
     */
    public void clearOther(@NonNull Disposable disposable) {
        if (!disposable.isDisposed() && mDisposable != null)
            mDisposable.delete(disposable);
    }

    /**
     * 移除其他的观察者，然后在调用{@link #clear()}，这样在界面消失后仍然能接收到回调
     *
     * @param disposables 此观察者数组不移除
     */
    public void clearOther(@NonNull Disposable... disposables) {
        if (mDisposable != null)
            for (int i = 0; i < disposables.length; i++) {
                if (disposables[i] != null && !disposables[i].isDisposed())
                    mDisposable.delete(disposables[i]);
            }
    }

    /**
     * 清除所有观察者，将不再接受被观察者的消息
     */
    public void clear() {
        if (mDisposable != null)
            mDisposable.clear();
        mDisposable = null;
    }
}
