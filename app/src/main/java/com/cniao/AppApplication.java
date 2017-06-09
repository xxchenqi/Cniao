package com.cniao;

import android.app.Application;
import android.content.Context;

import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerAppComponent;
import com.cniao.di.module.AppModule;
import com.cniao.di.module.HttpModule;

/**
 * Created by chenqi on 2017/6/8.
 */

public class AppApplication extends Application {
    private AppComponent mAppComponent;

    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }
}
