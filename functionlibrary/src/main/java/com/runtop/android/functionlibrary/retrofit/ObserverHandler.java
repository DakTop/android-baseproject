package com.runtop.android.functionlibrary.retrofit;

import android.content.Context;

import com.runtop.android.functionlibrary.view.dialog.ProgerssDialog;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObserverHandler<T> implements Observer<T> {

    private ProgerssDialog lodingDialog;
    private boolean showLodingDialog = true;
    private ObserverListener<T> observerListener;

    public ObserverHandler(Context context, String msg, ObserverListener observerListener) {
        lodingDialog = new ProgerssDialog(context, msg);
        this.observerListener = observerListener;
    }

    public ObserverHandler(Context context, String msg, boolean showLodingDialog, ObserverListener observerListener) {
        if (showLodingDialog) {
            lodingDialog = new ProgerssDialog(context, msg);
        }
        this.showLodingDialog = showLodingDialog;
        this.observerListener = observerListener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (showLodingDialog) {
            lodingDialog.show();
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
        observerListener.onFail("请求异常");
    }

    @Override
    public void onComplete() {
        cancelDialog();
    }

    private void cancelDialog() {
        if (lodingDialog != null) {
            lodingDialog.cancel();
        }
    }

}
