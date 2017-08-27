package com.androidaop.demo;

import android.app.Application;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        SecurityCheckManager.createInstance(this);
        super.onCreate();

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
