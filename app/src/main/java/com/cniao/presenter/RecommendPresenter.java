package com.cniao.presenter;

import android.Manifest;
import android.app.Activity;

import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.common.rx.RxHttpResponeCompat;
import com.cniao.common.rx.subscriber.ProgressSubscriber;
import com.cniao.data.RecommendModel;
import com.cniao.presenter.contract.RecommendContract;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }

    public void requestDatas() {
        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {

                    @Override
                    public Observable<PageBean<AppInfo>> call(Boolean aBoolean) {
                        if (aBoolean) {
                            return mModel.getApps().compose(RxHttpResponeCompat.<PageBean<AppInfo>>compatResult());
                        } else {
                            return Observable.empty();
                        }
                    }
                })
                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        mView.showResult(appInfoPageBean.getDatas());
                    }
                });

    }
}
