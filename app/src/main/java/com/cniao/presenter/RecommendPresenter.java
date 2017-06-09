package com.cniao.presenter;

import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.data.RecommendModel;
import com.cniao.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendPresenter implements RecommendContract.Presenter {
    private RecommendContract.View mView;
    private RecommendModel mModel;

//    @Inject
    public RecommendPresenter(RecommendContract.View mView, RecommendModel model) {
        this.mView = mView;
        mModel = model;
    }


    @Override
    public void requestDatas() {
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response != null) {
                    mView.showResult(response.body().getDatas());
                } else {
                    mView.showNoData();
                }
                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.showError(t.getMessage());
                mView.dismissLoading();
            }
        });
    }
}
