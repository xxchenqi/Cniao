package com.cniao.presenter;


import com.cniao.bean.AppInfo;
import com.cniao.common.rx.RxHttpResponeCompat;
import com.cniao.common.rx.subscriber.ProgressSubscriber;
import com.cniao.data.AppInfoModel;
import com.cniao.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.presenter
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class AppDetailPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppDetailView> {

    @Inject
    public AppDetailPresenter(AppInfoModel appInfoModel, AppInfoContract.AppDetailView appDetailView) {
        super(appInfoModel, appDetailView);
    }

    public void getAppDetail(int id){
        mModel.getAppDetail(id).compose(RxHttpResponeCompat.<AppInfo>compatResult())
                .subscribe(new ProgressSubscriber<AppInfo>(mContext,mView) {
                    @Override
                    public void onNext(AppInfo appInfo) {
                        mView.showAppDetail(appInfo);
                    }
                });
    }



}
