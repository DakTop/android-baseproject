package com.runtop.android.functionlibrary.retrofit;

public interface ObserverListener<T> {
    void onSuccess(T t);

    void onFail(String code,String msg);
}
