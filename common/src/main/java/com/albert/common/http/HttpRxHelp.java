package com.albert.common.http;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-15.
 *      Desc         : 网络请求帮助类，简化网络请求
 * </pre>
 */
public class HttpRxHelp {

    public static <T, R> void subscribe(Observable<T> observable, final HttpApiCastCallback<T, R> callback) {
        subscribe(observable, null, callback);
    }

    /**
     * 请求接口数据T 并转换为R
     *
     * @param observable 接口数据T
     * @param composite
     * @param callback
     * @param <T>        服务端数据模型
     * @param <R>        本地数据模型
     */
    public static <T, R> void subscribe(Observable<T> observable, CompositeApiObserver composite, final HttpApiCastCallback<T, R> callback) {
        observable
                .subscribeOn(Schedulers.computation())
                .map(new Function<T, R>() {
                    @Override
                    public R apply(@io.reactivex.annotations.NonNull T t) {
                        if (callback != null) {
                            return callback.onSucBefore(t);
                        } else {
                            return null;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<R>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        if (callback != null)
                            callback.onError(e);
                    }

                    @Override
                    public void onRxNext(@NonNull R s) {
                        if (callback != null)
                            callback.onSuc(s);
                    }

                    @Override
                    public void onRxComplete() {
                        if (callback != null)
                            callback.onComplete();
                    }
                });
    }

    /**
     * 把 x，y数据转成D
     *
     * @param ox
     * @param oy
     * @param composite
     * @param callback
     * @param <X>
     * @param <Y>
     * @param <D>
     */
    public static <X, Y, D> void subscribe(Observable<X> ox, Observable<Y> oy, CompositeApiObserver composite,
                                           final HttpApiCast2to1Callback<X, Y, D> callback) {
        Observable
                .zip(ox, oy, new BiFunction<X, Y, D>() {
                    @io.reactivex.annotations.NonNull
                    @Override
                    public D apply(@io.reactivex.annotations.NonNull X x, @io.reactivex.annotations.NonNull Y y) throws Exception {
                        if (callback != null) {
                            return callback.onSucBefore(x, y);
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<D>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onRxNext(@NonNull D d) {
                        if (callback != null) {
                            callback.onSuc(d);
                        }
                    }

                    @Override
                    public void onRxComplete() {
                        if (callback != null) {
                            callback.onComplete();
                        }
                    }
                });
    }

    /**
     * 把 x，y，z数据转成D
     *
     * @param ox
     * @param oy
     * @param oz
     * @param composite
     * @param callback
     * @param <X>
     * @param <Y>
     * @param <Z>
     * @param <D>
     */
    public static <X, Y, Z, D> void subscribe(Observable<X> ox, Observable<Y> oy, Observable<Z> oz, CompositeApiObserver composite,
                                              final HttpApiCast3to1Callback<X, Y, Z, D> callback) {
        Observable
                .zip(ox, oy, oz, new Function3<X, Y, Z, D>() {
                    @io.reactivex.annotations.NonNull
                    @Override
                    public D apply(@io.reactivex.annotations.NonNull X x, @io.reactivex.annotations.NonNull Y y, @io.reactivex.annotations.NonNull Z z) throws Exception {
                        if (callback != null) {
                            return callback.onSucBefore(x, y, z);
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<D>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onRxNext(@NonNull D d) {
                        if (callback != null) {
                            callback.onSuc(d);
                        }
                    }

                    @Override
                    public void onRxComplete() {
                        if (callback != null) {
                            callback.onComplete();
                        }
                    }
                });
    }

    /**
     * 把 x1，x2，x3，x4数据转成D
     *
     * @param ox1
     * @param ox2
     * @param ox3
     * @param ox4
     * @param composite
     * @param callback
     * @param <X1>
     * @param <X2>
     * @param <X3>
     * @param <X4>
     * @param <D>
     */
    public static <X1, X2, X3, X4, D> void subscribe(Observable<X1> ox1, Observable<X2> ox2, Observable<X3> ox3, Observable<X4> ox4,
                                                     CompositeApiObserver composite,
                                                     final HttpApiCast4to1Callback<X1, X2, X3, X4, D> callback) {
        Observable
                .zip(ox1, ox2, ox3, ox4, new Function4<X1, X2, X3, X4, D>() {
                    @io.reactivex.annotations.NonNull
                    @Override
                    public D apply(@io.reactivex.annotations.NonNull X1 x1, @io.reactivex.annotations.NonNull X2 x2, @io.reactivex.annotations.NonNull X3 x3, @io.reactivex.annotations.NonNull X4 x4) throws Exception {
                        if (callback != null) {
                            return callback.onSucBefore(x1, x2, x3, x4);
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<D>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onRxNext(@NonNull D d) {
                        if (callback != null) {
                            callback.onSuc(d);
                        }
                    }

                    @Override
                    public void onRxComplete() {
                        if (callback != null) {
                            callback.onComplete();
                        }
                    }
                });
    }

    /**
     * 把 x1，x2，x3，x4,x5数据转成D
     *
     * @param ox1
     * @param ox2
     * @param ox3
     * @param ox4
     * @param ox5
     * @param composite
     * @param callback
     * @param <X1>
     * @param <X2>
     * @param <X3>
     * @param <X4>
     * @param <X5>
     * @param <D>
     */
    public static <X1, X2, X3, X4, X5, D> void subscribe(Observable<X1> ox1, Observable<X2> ox2, Observable<X3> ox3, Observable<X4> ox4, Observable<X5> ox5,
                                                         CompositeApiObserver composite,
                                                         final HttpApiCast5to1Callback<X1, X2, X3, X4, X5, D> callback) {
        Observable
                .zip(ox1, ox2, ox3, ox4, ox5, new Function5<X1, X2, X3, X4, X5, D>() {
                    @io.reactivex.annotations.NonNull
                    @Override
                    public D apply(@io.reactivex.annotations.NonNull X1 x1, @io.reactivex.annotations.NonNull X2 x2,
                                   @io.reactivex.annotations.NonNull X3 x3, @io.reactivex.annotations.NonNull X4 x4,
                                   @io.reactivex.annotations.NonNull X5 x5) throws Exception {
                        if (callback != null) {
                            return callback.onSucBefore(x1, x2, x3, x4, x5);
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<D>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onRxNext(@NonNull D d) {
                        if (callback != null) {
                            callback.onSuc(d);
                        }
                    }

                    @Override
                    public void onRxComplete() {
                        if (callback != null) {
                            callback.onComplete();
                        }
                    }
                });
    }




    /*------------------------------------------------*/

    /**
     * 请求接口
     *
     * @param observable
     * @param composite
     * @param callback
     * @param <T>
     */
    public static <T> void subscribe(Observable<T> observable, CompositeApiObserver composite, final HttpApiCallback<T> callback) {

        observable.subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        callback.onSucBefore(t);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<T>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onRxNext(@NonNull T t) {
                        if (callback != null) {
                            callback.onSuc(t);
                        }
                    }

                    @Override
                    public void onRxComplete() {
                        if (callback != null) {
                            callback.onComplete();
                        }
                    }
                });
    }

    /**
     * rx网络请求
     *
     * @param observable
     * @param callback
     * @param <T>
     */
    public static <T> void subscribe(Observable<T> observable, final HttpApiCallback<T> callback) {
        observable.subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        callback.onSucBefore(t);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        callback.onSuc(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // 进行服务端报错的统一处理
                        if (throwable instanceof HttpErrorException) {
                            callback.onError((HttpErrorException) throwable);
                        } else {
                            HttpErrorException apiException = HttpApiExceptionEngine.handleException(throwable);
                            callback.onError(apiException);
                        }
                    }
                });
    }

    /**
     * rx网络请求,子线程
     *
     * @param observable
     * @param composite  请求管理者
     * @param callback
     * @param <T>
     */
    public static <T> void subscribeIoComposite(Observable<T> observable, final CompositeApiObserver composite, final HttpApiCallback<T> callback) {
        observable.subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        callback.onSucBefore(t);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<T>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onRxNext(@NonNull T t) {
                        callback.onSuc(t);
                    }

                    @Override
                    public void onRxComplete() {
                        callback.onComplete();
                    }
                });
    }

    /**
     * rx网络请求 返回在子线程
     *
     * @param observable
     * @param callback
     * @param <T>
     */
    public static <T> void subscribeIo(Observable<T> observable, final HttpApiCallback<T> callback) {
        observable.subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) {
                        if (null != callback) {
                            callback.onSuc(t);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        if (null != callback) {
                            // 进行服务端报错的统一处理s
                            if (throwable instanceof HttpErrorException) {
                                callback.onError((HttpErrorException) throwable);
                            } else {
                                HttpErrorException apiException = HttpApiExceptionEngine.handleException(throwable);
                                callback.onError(apiException);
                            }
                        }
                    }
                });
    }

    /**
     * 当前线程处理（同步）
     *
     * @param observable
     * @param callback
     * @param <T>
     */
    public static <T> void subscribeSync(Observable<T> observable, final HttpApiCallback<T> callback) {
        subscribeSync(observable, null, callback);
    }

    /**
     * 当前线程处理（同步）
     *
     * @param observable
     * @param composite
     * @param callback
     * @param <T>
     */
    public static <T> void subscribeSync(Observable<T> observable, CompositeApiObserver composite, final HttpApiCallback<T> callback) {
        observable
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        callback.onSucBefore(t);
                    }
                })
                .subscribe(new HttpApiObserve<T>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onRxNext(@NonNull T t) {
                        callback.onSuc(t);
                    }

                    @Override
                    public void onRxComplete() {
                        callback.onComplete();
                    }
                });
    }

    /**
     * rx网络请求 返回在子线程
     *
     * @param observable
     * @param composite
     * @param callback
     * @param <T>
     */
    public static <T> void httpSubscribeIo(Observable<T> observable, CompositeApiObserver composite, final HttpApiCallback<T> callback) {
        observable.subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        callback.onSucBefore(t);
                    }
                })
                .observeOn(Schedulers.computation())
                .subscribe(new HttpApiObserve<T>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onRxNext(@NonNull T t) {
                        callback.onSuc(t);
                    }

                    @Override
                    public void onRxComplete() {
                        callback.onComplete();
                    }
                });
    }

    /**
     * 两个接口组合，等待全部请求完成后才返回
     *
     * @param ox
     * @param oy
     * @param callback
     * @param <X>
     * @param <Y>
     * @return
     */
    public static <X, Y> Disposable subscribe(Observable<X> ox, Observable<Y> oy, final HttpApiCallback2<X, Y> callback) {
        Disposable subscribe = null;
        subscribe = Observable
                .zip(ox, oy, new BiFunction<X, Y, Object>() {
                    @Override
                    public Object apply(@io.reactivex.annotations.NonNull final X x, @io.reactivex.annotations.NonNull final Y y) throws Exception {
                        if (callback != null) {
                            callback.onSucBefore(x, y);
                        }
                        return new Object[]{x, y};
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {
                        Object[] array = (Object[]) o;
                        callback.onSuc((X) array[0], (Y) array[1]);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        // 进行服务端报错的统一处理
                        HttpErrorException apiException = HttpApiExceptionEngine.handleException(throwable);
                        callback.onError(apiException);
                    }
                });
        return subscribe;
    }

    /**
     * 两个接口组合，等待全部请求完成后才返回
     *
     * @param ox
     * @param oy
     * @param composite
     * @param callback
     * @param <X>
     * @param <Y>
     */
    public static <X, Y> void subscribe(Observable<X> ox, Observable<Y> oy,
                                        CompositeApiObserver composite, final HttpApiCallback2<X, Y> callback) {
        Observable.zip(ox, oy, (BiFunction<X, Y, Object>) (x, y) -> {
            if (callback != null) {
                callback.onSucBefore(x, y);
            }
            // 合并一个发送出去
            return new Object[]{x, y};
        }).subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Object[] array = (Object[]) o;
                        callback.onSucBefore((X) array[0], (Y) array[1]);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<Object>(composite) {

                    @Override
                    public void onRxNext(@NonNull Object o) {
                        Object[] array = (Object[]) o;
                        callback.onSuc((X) array[0], (Y) array[1]);
                    }

                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onRxComplete() {
                        callback.onComplete();
                    }
                });
    }

    /**
     * 两个接口组合,运行在子线程 等待全部请求完成后才返回
     *
     * @param ox
     * @param oy
     * @param composite
     * @param callback
     * @param <X>
     * @param <Y>
     */
    public static <X, Y> void subscribeIo(Observable<X> ox, Observable<Y> oy,
                                          CompositeApiObserver composite, final HttpApiCallback2<X, Y> callback) {
        Observable.zip(ox, oy, (BiFunction<X, Y, Object>) (x, y) -> {
            // 合并一个发送出去
            return new Object[]{x, y};
        }).subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Object[] array = (Object[]) o;
                        callback.onSucBefore((X) array[0], (Y) array[1]);
                    }
                })
                .observeOn(Schedulers.computation())
                .subscribe(new HttpApiObserve<Object>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onRxNext(@NonNull Object o) {
                        Object[] array = (Object[]) o;
                        callback.onSuc((X) array[0], (Y) array[1]);
                    }

                    @Override
                    public void onRxComplete() {
                        callback.onComplete();
                    }
                });
    }

    /**
     * 合并三个接口，发送一个数据 等待全部请求完成后才返回
     *
     * @param ox
     * @param oy
     * @param oz
     * @param callback
     * @param <X>
     * @param <Y>
     * @param <Z>
     * @return
     */
    public static <X, Y, Z> Disposable subscribe(Observable<X> ox, Observable<Y> oy, Observable<Z> oz, final HttpApiCallback3<X, Y, Z> callback) {
        Disposable subscribe = null;
        try {
            subscribe = Observable
                    .zip(ox, oy, oz, (Function3<X, Y, Z, Object>) (x, y, z) -> {
                        if (callback != null) {
                            callback.onSucBefore(x, y, z);
                        }
                        // 合并一个发送出去
                        return new Object[]{x, y, z};
                    })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o -> {
                        Object[] array = (Object[]) o;
                        callback.onSuc((X) array[0], (Y) array[1], (Z) array[2]);
                    }, throwable -> {
                        // 进行服务端报错的统一处理
                        HttpErrorException apiException = HttpApiExceptionEngine.handleException(throwable);
                        callback.onError(apiException);
                    });
        } catch (Throwable e) {
            HttpErrorException apiException = HttpApiExceptionEngine.handleException(e);
            callback.onError(apiException);
        }
        return subscribe;
    }

    /**
     * 合并三个接口，发送一个数据，等待全部请求完成后才返回
     *
     * @param ox
     * @param oy
     * @param oz
     * @param composite
     * @param callback
     * @param <X>
     * @param <Y>
     * @param <Z>
     */
    public static <X, Y, Z> void subscribe(Observable<X> ox, Observable<Y> oy, Observable<Z> oz,
                                           CompositeApiObserver composite, final HttpApiCallback3<X, Y, Z> callback) {
        Observable.zip(ox, oy, oz, (Function3<X, Y, Z, Object>) (x, y, z) -> {
            // 合并一个发送出去
            return new Object[]{x, y, z};
        }).subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Object[] array = (Object[]) o;
                        callback.onSucBefore((X) array[0], (Y) array[1], (Z) array[2]);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<Object>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onRxNext(@NonNull Object o) {
                        Object[] array = (Object[]) o;
                        callback.onSuc((X) array[0], (Y) array[1], (Z) array[2]);
                    }

                    @Override
                    public void onRxComplete() {
                        callback.onComplete();
                    }
                });
    }


    /**
     * 合并处理：无序，如果有一个接口报错了，会延迟错误处理，后面的接口会继续执行没有被中断
     *
     * @param ox
     * @param oy
     * @param composite
     * @param callback
     * @param <X>
     * @param <Y>
     */
    public static <X, Y> void subscribe(Observable<X> ox, Observable<Y> oy,
                                        CompositeApiObserver composite, final HttpApiCallback<Object> callback) {
        mergeDelayError(Observable.mergeDelayError(ox, oy), composite, callback);
    }

    /**
     * 合并处理：无序，如果有一个接口报错了，会延迟错误处理，后面的接口会继续执行没有被中断
     *
     * @param ox
     * @param oy
     * @param oz
     * @param composite
     * @param callback
     * @param <X>
     * @param <Y>
     * @param <Z>
     */
    public static <X, Y, Z> void subscribe(Observable<X> ox, Observable<Y> oy, Observable<Z> oz,
                                           CompositeApiObserver composite, final HttpApiCallback<Object> callback) {
        mergeDelayError(Observable.mergeDelayError(ox, oy, oz), composite, callback);
    }

    /**
     * 合并处理：无序，如果有一个接口报错了，会延迟错误处理，后面的接口会继续执行没有被中断
     *
     * @param oW
     * @param ox
     * @param oy
     * @param oz
     * @param composite
     * @param callback
     * @param <W>
     * @param <X>
     * @param <Y>
     * @param <Z>
     */
    public static <W, X, Y, Z> void subscribe(Observable<W> oW, Observable<X> ox, Observable<Y> oy, Observable<Z> oz,
                                              CompositeApiObserver composite, final HttpApiCallback<Object> callback) {
        mergeDelayError(Observable.mergeDelayError(oW, ox, oy, oz), composite, callback);
    }

    /**
     * mergeDelayError合并的请求，无序，如果有一个接口报错了，会延迟错误处理，后面的接口会继续执行没有被中断
     *
     * @param observable
     * @param composite
     * @param callback
     * @param <T>
     */
    private static <T> void mergeDelayError(Observable<T> observable, CompositeApiObserver composite, final HttpApiCallback<Object> callback) {
        observable
                .subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) {
                        callback.onSucBefore(o);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpApiObserve<Object>(composite) {
                    @Override
                    public void onRxError(@NonNull HttpErrorException e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onRxNext(@NonNull Object o) {
                        callback.onSuc(o);
                    }

                    @Override
                    public void onRxComplete() {
                        callback.onComplete();
                    }
                });
    }


}
