package com.runtop.android.core.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runtop.android.core.base.BasePresenter;
import com.runtop.android.core.base.IBaseView;
import com.trello.rxlifecycle4.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter> extends RxFragment {


    protected BasePresenter presenter;
    private Unbinder unbinder = null;
    private View parentView = null;
    private boolean isCreated = false;
    private boolean isFirstUserVisible = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int layoutResId = this.setLayoutId();
        if (layoutResId != 0) {
            if (null == parentView) {
                parentView = inflater.inflate(layoutResId, null);
            }
            unbinder = ButterKnife.bind(this, parentView);
            isCreated = true;
            if (presenter == null) {
                presenter = obtainPresenter();
                presenter.createPresenter((IBaseView) this);
            }
            this.initView(savedInstanceState);
            if (useEventBus()) {
                EventBus.getDefault().register(this);
            }
            return parentView;
        } else {
            throw new NullPointerException("createView don't be null");
        }

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isCreated) {
                if (isFirstUserVisible) {
                    isFirstUserVisible = false;
                } else {
                    this.resumeView();
                }
            }
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            resumeView();
        } else {
            hidden();
        }
    }


    protected void resumeView() {

    }

    protected void hidden() {

    }


    public abstract void initView(Bundle savedInstanceState);


    public boolean useEventBus() {
        return false;
    }

    public int setLayoutId() {
        return 0;
    }

    public BasePresenter obtainPresenter() {
        return null;
    }


    @Override
    public void onDestroy() {
        isFirstUserVisible = true;
        isCreated = false;
        this.destroyView();
        if (presenter != null) {
            presenter.destroyPresenter();
        }
        presenter = null;
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    public void destroyView() {

    }

}
