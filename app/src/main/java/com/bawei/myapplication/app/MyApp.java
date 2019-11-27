package com.bawei.myapplication.app;

import android.app.Application;
import android.content.Context;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/11/27
 */
public class MyApp extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
