package com.cniao.di.component;

import com.cniao.di.FragmentScope;
import com.cniao.di.module.AppInfoModule;
import com.cniao.ui.fragment.CategoryAppFragment;
import com.cniao.ui.fragment.GamesFragment;
import com.cniao.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by chenqi on 2017/8/24.
 */
@FragmentScope
@Component(modules = AppInfoModule.class, dependencies = AppComponent.class)
public interface AppInfoComponent {
    void injectTopListFragment(TopListFragment fragment);
    void injectGamesFragment(GamesFragment fragment);
    void injectCategoryAppFragment(CategoryAppFragment fragment);
}
