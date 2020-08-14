package com.lieying.lydemo3.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lieying.lydemo3.net.NetWorkManager;


public class MyApplication extends Application {
    String LicenceUrl = "http://license.vod2.myqcloud.com/license/v1/d37270cbe9642e1e7f1effc6b8d1fc7e/TXLiveSDK.licence";
    String Key = "115d98d391059cd4254caf31b9212f62";
    private boolean isDebugARouter = true;
    @Override
    public void onCreate() {
        super.onCreate();
        if(isDebugARouter){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        NetWorkManager.getInstance().init();
    }
}
