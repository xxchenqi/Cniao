package com.cniao.di.module;

import com.cniao.AppApplication;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenqi on 2017/6/8.
 */
@Module
public class AppModule {
    private AppApplication mApplication;

    public AppModule(AppApplication appApplication) {
        this.mApplication = appApplication;
    }

    @Provides
    @Singleton
    public AppApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

}
