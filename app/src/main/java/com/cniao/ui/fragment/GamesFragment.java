package com.cniao.ui.fragment;

import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerAppInfoComponent;
import com.cniao.di.module.AppInfoModule;
import com.cniao.presenter.AppInfoPresenter;
import com.cniao.ui.adapter.AppInfoAdapter;

/**
 * Created by chenqi on 2017/6/6.
 */

public class GamesFragment extends BaseAppInfoFragment {
    @Override
    int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this)).build().injectGamesFragnebt(this);
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showBrief(true).showCategoryName(true).showPosition(false).build();
    }
}
