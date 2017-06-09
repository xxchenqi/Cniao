package com.cniao.di.component;

import com.cniao.di.FragmentScope;
import com.cniao.di.module.RecommendModule;
import com.cniao.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by chenqi on 2017/6/8.
 */
@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}
