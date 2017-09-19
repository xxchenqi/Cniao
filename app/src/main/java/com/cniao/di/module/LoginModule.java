package com.cniao.di.module;

import com.cniao.data.LoginModel;
import com.cniao.data.http.ApiService;
import com.cniao.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenqi on 2017/9/19.
 */
@Module
public class LoginModule {
    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView mView) {
        this.mView = mView;
    }

    @Provides
    public LoginContract.LoginView provideView() {
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel provideModel(ApiService apiService) {
        return new LoginModel(apiService);
    }
}
