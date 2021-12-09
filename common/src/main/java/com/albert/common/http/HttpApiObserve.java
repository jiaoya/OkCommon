package com.albert.common.http;

import androidx.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-15.
 *      Desc         : rx网络请求回调的统一封装
 * </pre>
 */
public abstract class HttpApiObserve<T> implements Observer<T> {

    private Disposable tDisposable = null;
    /**
     * 管理回调
     */
    private CompositeApiObserver mComposite;

    public HttpApiObserve(CompositeApiObserver composite) {
        mComposite = composite;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (mComposite != null) {//添加到观察者统一管理
            tDisposable = d;
            mComposite.add(d);
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        onRxNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        // 进行服务端报错的统一处理
        if (e instanceof HttpErrorException) {
            onRxError((HttpErrorException) e);
        } else {
            HttpErrorException apiException = HttpApiExceptionEngine.handleException(e);
            onRxError(apiException);
        }

        onRxComplete();

    }

    @Override
    public void onComplete() {
        onRxComplete();
    }

    public void onRxError(@NonNull HttpErrorException e) {

    }

    public void onRxNext(@NonNull T t) {

    }

    public void onRxComplete() {
        
    }

    public Disposable gettDisposable() {
        return tDisposable;
    }
}
