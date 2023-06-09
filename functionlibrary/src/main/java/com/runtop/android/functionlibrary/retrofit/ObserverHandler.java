package com.runtop.android.functionlibrary.retrofit;

import android.content.Context;

import com.runtop.android.functionlibrary.view.dialog.ProgressDialog;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable;
import io.reactivex.rxjava3.internal.util.EndConsumerHelper;
import retrofit2.HttpException;

public class ObserverHandler<T> implements Observer<T>, Disposable {

    private ProgressDialog loadingDialog;
    private boolean showLoadingDialog = true;
    //
    /**
     * The active subscription.
     */
    private final AtomicReference<Disposable> upstream = new AtomicReference<>();
    /**
     * The resource composite, can never be null.
     */
    private final ListCompositeDisposable resources = new ListCompositeDisposable();
    private ObserverListener<T> observerListener;

    public ObserverHandler(ObserverListener observerListener) {
        this.observerListener = observerListener;
    }

    public ObserverHandler(Context context, ObserverListener observerListener) {
        if (showLoadingDialog) {
            loadingDialog = new ProgressDialog(context, "加载中...");
            loadingDialog.show();
        }
        this.observerListener = observerListener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (EndConsumerHelper.setOnce(this.upstream, d, getClass())) {
            add(d);
        }
    }

    @Override
    public void onNext(T t) {
        cancelDialog();
        observerListener.onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        cancelDialog();
        e.printStackTrace();
        if (observerListener != null) {
            if (e instanceof UnknownHostException) {
                observerListener.onFail("404", "404网络不可用");
            } else if (e instanceof SocketTimeoutException) {
                observerListener.onFail("408", "408连接超时");
            } else if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                observerListener.onFail(httpException.code() + "", httpException.message());
            } else if (e instanceof ParseException || e instanceof JSONException) {
                observerListener.onFail("-100", "json数据解析错误");
            } else {
                observerListener.onFail("-100", e.toString());
            }
        }
    }

    @Override
    public void onComplete() {
        cancelDialog();
    }


    /**
     * Adds a resource to this ResourceObserver.
     *
     * @param resource the resource to add
     * @throws NullPointerException if resource is null
     */
    public final void add(@NonNull Disposable resource) {
        resources.add(resource);
    }

    private void cancelDialog() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
    }

    @Override
    public void dispose() {
        if (DisposableHelper.dispose(upstream)) {
            resources.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(upstream.get());
    }
}
