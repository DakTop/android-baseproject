package com.runtop.android.client.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.runtop.android.client.bean.LoginResp;
import com.runtop.android.client.presenter.iview.MainIView;
import com.runtop.android.core.provider.RequestFactory;
import com.runtop.android.core.provider.RequestManager;
import com.runtop.android.core.base.BasePresenter;
import com.runtop.android.core.base.bean.BaseResponse;
import com.runtop.android.functionlibrary.retrofit.ObserverHandler;
import com.runtop.android.functionlibrary.retrofit.ObserverListener;
import com.trello.rxlifecycle4.LifecycleProvider;

public class MainPresenter extends BasePresenter<MainIView> {

    public void login() {

        RequestManager.getTestApi()
                .login(RequestFactory.obtainRequest()
                        .addParam("username", "oyll123")
                        .addParam("password", "111qqq")
                        .build())
                .compose(rxSchedulingThread())
                .compose(bindToLifecycle((LifecycleProvider) view))
                .subscribe(new ObserverHandler<BaseResponse<LoginResp>>((Context) view, new ObserverListener<BaseResponse<LoginResp>>() {
                    @Override
                    public void onSuccess(BaseResponse<LoginResp> o) {
                        Log.i("返回日志", "_" + o.getData());
                        view.OnGoodsSuccess();
                    }

                    @Override
                    public void onFail(String code, String msg) {

                    }
                }));

    }
}
