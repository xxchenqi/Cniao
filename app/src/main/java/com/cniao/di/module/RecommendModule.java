package com.cniao.di.module;

import android.app.ProgressDialog;

import com.cniao.data.RecommendModel;
import com.cniao.data.http.ApiService;
import com.cniao.presenter.RecommendPresenter;
import com.cniao.presenter.contract.RecommendContract;
import com.cniao.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenqi on 2017/6/8.
 */
@Module
public class RecommendModule {
    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public RecommendContract.View providerView() {
        return mView;
    }

    @Provides
    public RecommendContract.Presenter providerPresenter(RecommendContract.View view, RecommendModel model) {
        return new RecommendPresenter(view, model);
    }

    @Provides
    public ProgressDialog providerProgressDialog(RecommendContract.View view) {
        return new ProgressDialog(((RecommendFragment) view).getActivity());
    }

    @Provides
    public RecommendModel providerModel(ApiService apiService) {
        return new RecommendModel(apiService);
    }
}
