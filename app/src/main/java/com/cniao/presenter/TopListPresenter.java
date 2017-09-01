package com.cniao.presenter;

import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.common.rx.RxHttpResponeCompat;
import com.cniao.common.rx.subscriber.ErrorHandlerSubscriber;
import com.cniao.common.rx.subscriber.ProgressSubscriber;
import com.cniao.data.AppInfoModel;
import com.cniao.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by chenqi on 2017/8/24.
 */

public class TopListPresenter extends BasePresenter<AppInfoModel, AppInfoContract.TopListView> {
    @Inject
    public TopListPresenter(AppInfoModel appInfoModel, AppInfoContract.TopListView topListView) {
        super(appInfoModel, topListView);
    }

    public void getTopListApps(int page) {
        Subscriber subscriber = null;
        if (page == 0) {
            //加载第一页
            subscriber = new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        } else {
            //加载第下页
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {
                @Override
                public void onCompleted() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<AppInfo> pageBeanSubscriber) {
                    mView.showResult(pageBeanSubscriber);
                }
            };
        }

        mModel.topList(page)
                .compose(RxHttpResponeCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }


}
