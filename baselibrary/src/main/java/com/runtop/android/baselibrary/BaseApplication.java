package com.runtop.android.baselibrary;

import android.app.Application;

public class BaseApplication extends Application {

    private  static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }
}
