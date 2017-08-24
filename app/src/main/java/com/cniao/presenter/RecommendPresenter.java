package com.cniao.presenter;

import com.cniao.bean.IndexBean;
import com.cniao.common.rx.RxHttpResponeCompat;
import com.cniao.common.rx.subscriber.ProgressSubscriber;
import com.cniao.data.RecommendModel;
import com.cniao.presenter.contract.RecommendContract;

import javax.inject.Inject;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }

    public void requestDatas() {
//        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
//
//                    @Override
//                    public Observable<PageBean<AppInfo>> call(Boolean aBoolean) {
//                        if (aBoolean) {
//                            return mModel.getApps().compose(RxHttpResponeCompat.<PageBean<AppInfo>>compatResult());
//                        } else {
//                            return Observable.empty();
//                        }
//                    }
//                })
//                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        mView.showResult(appInfoPageBean.getDatas());
//                    }
//                });

        mModel.index().compose(RxHttpResponeCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubscriber<IndexBean>(mContext, mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResult(indexBean);
                    }
                });

    }
}
