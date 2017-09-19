package com.cniao.data;

import com.cniao.bean.BaseBean;
import com.cniao.bean.LoginBean;
import com.cniao.bean.requestbean.LoginRequestBean;
import com.cniao.data.http.ApiService;
import com.cniao.presenter.contract.LoginContract;

import rx.Observable;

/**
 * Created by chenqi on 2017/9/19.
 */

public class LoginModel implements LoginContract.ILoginModel {
    private ApiService apiService;

    public LoginModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(pwd);
        return apiService.login(param);
    }
}
