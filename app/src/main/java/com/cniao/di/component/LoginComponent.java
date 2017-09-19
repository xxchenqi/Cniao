package com.cniao.di.component;

import com.cniao.di.FragmentScope;
import com.cniao.di.module.LoginModule;
import com.cniao.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by chenqi on 2017/9/19.
 */
@FragmentScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
