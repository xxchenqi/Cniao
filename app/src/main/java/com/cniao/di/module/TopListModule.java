package com.cniao.di.module;

import com.cniao.data.AppInfoModel;
import com.cniao.data.http.ApiService;
import com.cniao.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenqi on 2017/8/24.
 */
@Module
public class TopListModule {
    private AppInfoContract.TopListView mView;

    public TopListModule(AppInfoContract.TopListView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.TopListView providerView() {
        return mView;
    }

    @Provides
    public AppInfoModel providerModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }
}
