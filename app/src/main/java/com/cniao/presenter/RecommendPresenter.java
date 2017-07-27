package com.cniao.presenter;

import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
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
        mModel.getApps()
                .compose(RxHttpResponeCompat.<PageBean<AppInfo>>compatResult())
                //订阅
                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        mView.showResult(appInfoPageBean.getDatas());
                    }
                });
    }
}
