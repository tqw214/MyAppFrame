package com.viger.skin.application;

import android.app.Application;
import com.viger.skin.skin.SkinEngine;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化换肤引擎
        SkinEngine.getInstance().init(this);
    }
}
