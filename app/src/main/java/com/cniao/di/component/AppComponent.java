package com.cniao.di.component;

import android.app.Application;

import com.cniao.common.rx.RxErrorHandler;
import com.cniao.data.http.ApiService;
import com.cniao.di.module.AppModule;
import com.cniao.di.module.DownloadModule;
import com.cniao.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by chenqi on 2017/6/8.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class, DownloadModule.class})
public interface AppComponent {
    public ApiService getApiService();

    public Application getApplication();

    public RxErrorHandler getRxErrorHandler();

    public RxDownload getRxDownload();
}
