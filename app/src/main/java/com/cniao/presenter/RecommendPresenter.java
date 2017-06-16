package com.cniao.presenter;

import com.cniao.bean.AppInfo;
import com.cniao.bean.BaseBean;
import com.cniao.bean.PageBean;
import com.cniao.common.rx.RxErrorHandler;
import com.cniao.common.rx.RxHttpResponeCompat;
import com.cniao.common.rx.subscriber.ErrorHandlerSubscriber;
import com.cniao.data.RecommendModel;
import com.cniao.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

    private RxErrorHandler mRxErrorHandler;

    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view, RxErrorHandler errorHandler) {
        super(model, view);
        this.mRxErrorHandler = errorHandler;
    }

    public void requestDatas() {
        mModel.getApps()
                //请求时候在子线程
//                .subscribeOn(Schedulers.io())
                //请求完成后的操作在主线程
//                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxHttpResponeCompat.<PageBean<AppInfo>>compatResult())
                //订阅
                .subscribe(new ErrorHandlerSubscriber<PageBean<AppInfo>>(mRxErrorHandler) {
                    @Override
                    public void onStart() {
                        mView.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {
                            mView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mView.showNoData();
                        }
                    }
                });
    }
}
