package com.cniao.di.module;

import android.app.ProgressDialog;

import com.cniao.data.AppInfoModel;
import com.cniao.data.http.ApiService;
import com.cniao.presenter.contract.AppInfoContract;
import com.cniao.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenqi on 2017/6/8.
 */
@Module
public class RecommendModule {
    private AppInfoContract.View mView;

    public RecommendModule(AppInfoContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.View providerView() {
        return mView;
    }

//    @Provides
//    public AppInfoContract.Presenter providerPresenter(AppInfoContract.View view, AppInfoModel model) {
//        return new RecommendPresenter(view, model);
//    }

    @Provides
    public ProgressDialog providerProgressDialog(AppInfoContract.View view) {
        return new ProgressDialog(((RecommendFragment) view).getActivity());
    }

    @Provides
    public AppInfoModel providerModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }
}
