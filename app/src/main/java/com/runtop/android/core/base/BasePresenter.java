package com.runtop.android.core.base;

import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.LifecycleTransformer;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle4.components.support.RxDialogFragment;
import com.trello.rxlifecycle4.components.support.RxFragment;


import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BasePresenter<V extends IBaseView> {

    protected String TAG = null;
    protected V view;
    private CompositeDisposable compositeDisposable;

    public void createPresenter(V view) {
        TAG = getClass().getSimpleName();
        this.view = view;
    }

    public void destroyPresenter() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        this.compositeDisposable = null;
        this.view = null;
    }

    public void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }


    /**
     * 绑定 Activity/Fragment 的生命周期
     *
     * @param
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull LifecycleProvider lifecycleProvider) {
        if (lifecycleProvider instanceof RxAppCompatActivity) {
            return lifecycleProvider.bindToLifecycle();
        } else if (lifecycleProvider instanceof RxFragment) {
            return lifecycleProvider.bindToLifecycle();
        } else if (lifecycleProvider instanceof RxDialogFragment) {
            return lifecycleProvider.bindToLifecycle();
        } else {
            throw new IllegalArgumentException("lifecycleProvider not match");
        }
    }


    public static <T> ObservableTransformer<T, T> rxSchedulingThread() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
