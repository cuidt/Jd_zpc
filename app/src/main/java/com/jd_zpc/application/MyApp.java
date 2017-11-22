package com.jd_zpc.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by DELL on 2017/11/7.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        Fresco.initialize(this);
    }
    {
        PlatformConfig.setWeixin("","");
        PlatformConfig.setQQZone("1106390723","qDdup5FjlJOKaBKt");
        PlatformConfig.setSinaWeibo("","","");
    }

}
