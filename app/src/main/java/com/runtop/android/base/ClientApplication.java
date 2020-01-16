package com.runtop.android.base;

import android.app.Application;

import com.runtop.android.functionlibrary.App.AppDensity;

public class ClientApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppDensity.setDensity(this, 360);
    }
}
