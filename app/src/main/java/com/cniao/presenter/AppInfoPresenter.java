package com.cniao.presenter;

import com.cniao.bean.AppInfo;
import com.cniao.bean.BaseBean;
import com.cniao.bean.PageBean;
import com.cniao.common.rx.RxHttpResponeCompat;
import com.cniao.common.rx.subscriber.ErrorHandlerSubscriber;
import com.cniao.common.rx.subscriber.ProgressSubscriber;
import com.cniao.data.AppInfoModel;
import com.cniao.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by chenqi on 2017/8/24.
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {
    public static final int TOP_LIST = 1;
    public static final int GAME = 2;

    @Inject
    public AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView topListView) {
        super(appInfoModel, topListView);
    }

    public void requestData(int type, int page) {
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
        Observable observable = getObservable(type, page);

        observable
                .compose(RxHttpResponeCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page) {
        switch (type) {
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.games(page);
            default:
                return Observable.empty();
        }
    }


}
