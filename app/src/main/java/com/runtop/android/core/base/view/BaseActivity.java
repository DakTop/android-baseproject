package com.runtop.android.core.base.view;

import android.os.Bundle;

import com.runtop.android.core.base.BasePresenter;
import com.runtop.android.core.base.IBaseView;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity {

    protected T presenter;
    private Unbinder unbinder = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = setLayoutId();
        if (layoutResId != 0) {
            setContentView(layoutResId);
            unbinder = ButterKnife.bind(this);
        } else {
            throw new NullPointerException("createView don't be null");
        }
        if (presenter == null) {
            presenter = obtainPresenter();
            presenter.createPresenter((IBaseView) this);
        }

        this.initView(savedInstanceState);

        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    public abstract void initView(Bundle savedInstanceState);

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void onEventReceive(Object receive) {
        onEventReceive(receive.getClass().getSimpleName(), receive);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    private void onStickyEventReceive(Object receive) {
    }

    public void onEventReceive(String name, Object receive) {

    }


    public boolean useEventBus() {
        return false;
    }

    public abstract int setLayoutId();

    public T obtainPresenter() {
        return null;
    }


    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.destroyPresenter();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }


}
