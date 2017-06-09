package com.cniao.presenter;

import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.data.RecommendModel;
import com.cniao.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {
    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }

    public void requestDatas() {
        mModel.getApps()
                //请求时候在子线程
                .subscribeOn(Schedulers.io())
                //请求完成后的操作在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //订阅
                .subscribe(new Subscriber<PageBean<AppInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
                mView.dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissLoading();
            }

            @Override
            public void onNext(PageBean<AppInfo> response) {
                if (response != null) {
                    mView.showResult(response.getDatas());
                } else {
                    mView.showNoData();
                }
            }
        });
    }
}
