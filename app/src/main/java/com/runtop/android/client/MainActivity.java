package com.runtop.android.client;

import android.os.Bundle;
import android.view.View;

import com.runtop.android.client.presenter.MainPresenter;
import com.runtop.android.client.presenter.iview.MainIView;
import com.runtop.android.core.base.view.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainIView {

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter obtainPresenter() {
        return new MainPresenter();
    }
    @OnClick({R.id.net_request})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.net_request:
                presenter.login();
                break;
            default:
                break;
        }

    }

    @Override
    public void OnGoodsSuccess() {

    }
}
