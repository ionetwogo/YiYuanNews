package com.doublecc.yiyuannews;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this,"3e4fac54b5e114e07bf40ed92bc87796");
    }
}
