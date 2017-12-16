package com.cniao.ui.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerAppInfoComponent;
import com.cniao.di.module.AppInfoModule;
import com.cniao.presenter.AppInfoPresenter;
import com.cniao.presenter.contract.AppInfoContract;
import com.cniao.ui.adapter.AppInfoAdapter;

/**
 * Created by chenqi on 2017/6/6.
 */

public class TopListFragment extends BaseAppInfoFragment implements AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {

    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder()
                .showBrief(false)
                .showCategoryName(true)
                .showPosition(true)
                .rxDownload(mRxDownload)
                .build();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this)).build().injectTopListFragment(this);
    }

}
