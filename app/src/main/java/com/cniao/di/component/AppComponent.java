package com.cniao.di.component;

import com.cniao.data.http.ApiService;
import com.cniao.di.module.AppModule;
import com.cniao.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chenqi on 2017/6/8.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    public ApiService getApiService();
}
