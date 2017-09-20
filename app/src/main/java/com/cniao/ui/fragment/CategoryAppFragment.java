package com.cniao.ui.fragment;


import android.annotation.SuppressLint;

import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerAppInfoComponent;
import com.cniao.di.module.AppInfoModule;
import com.cniao.ui.adapter.AppInfoAdapter;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.ui.fragment
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment {

    private int categoryId;
    private int mFlagType;

    @SuppressLint("ValidFragment")
    public CategoryAppFragment(int categoryId, int flagType) {
        this.categoryId = categoryId;
        this.mFlagType = flagType;
    }


    @Override
    public void init() {
        mPresenter.requestCategoryApps(categoryId, page, mFlagType);
        initRecyclerView();
    }

    @Override
    int type() {
        return 0;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(false).build();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectCategoryAppFragment(this);
    }
}
