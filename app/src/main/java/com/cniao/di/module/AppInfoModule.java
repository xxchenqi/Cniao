package com.cniao.di.module;

import com.cniao.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenqi on 2017/8/24.
 */
@Module(includes = {AppModelModule.class})
public class AppInfoModule {
    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.AppInfoView providerView() {
        return mView;
    }

}
