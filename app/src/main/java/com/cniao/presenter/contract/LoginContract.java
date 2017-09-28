package com.cniao.presenter.contract;

import com.cniao.bean.BaseBean;
import com.cniao.bean.LoginBean;
import com.cniao.ui.BaseView;

import io.reactivex.Observable;


/**
 * Created by chenqi on 2017/9/19.
 */

public class LoginContract {

    public interface ILoginModel {
        Observable<BaseBean<LoginBean>> login(String phone, String pwd);
    }

    public interface LoginView extends BaseView {
        void checkPhoneError();

        void checkPhoneSuccess();

        void loginSuccess(LoginBean bean);
    }
}