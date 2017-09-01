package com.cniao.di.component;

import com.cniao.di.FragmentScope;
import com.cniao.di.module.TopListModule;
import com.cniao.ui.fragment.TopListFragment;


import dagger.Component;

/**
 * Created by chenqi on 2017/8/24.
 */
@FragmentScope
@Component(modules = TopListModule.class, dependencies = AppComponent.class)
public interface TopListComponent {
    void inject(TopListFragment topListFragment);
}
