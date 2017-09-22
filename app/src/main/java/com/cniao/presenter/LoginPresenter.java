package com.cniao.presenter;

import com.cniao.bean.LoginBean;
import com.cniao.common.Constant;
import com.cniao.common.rx.RxHttpResponeCompat;
import com.cniao.common.rx.subscriber.ErrorHandlerSubscriber;
import com.cniao.common.util.ACache;
import com.cniao.common.util.VerificationUtils;
import com.cniao.presenter.contract.LoginContract;
import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

/**
 * Created by chenqi on 2017/9/19.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.LoginView> {

    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }

    public void login(String phone, String pwd) {
        if (!VerificationUtils.matcherPhoneNum(phone)) {
            mView.checkPhoneError();
            return;
        } else {
            mView.checkPhoneSuccess();
        }

        mModel.login(phone, pwd).compose(RxHttpResponeCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
                    @Override
                    public void onStart() {
                        mView.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                        saveUser(loginBean);
                        RxBus.get().post(loginBean.getUser());
                    }
                });
    }

    private void saveUser(LoginBean bean){
        ACache aCache = ACache.get(mContext);
        aCache.put(Constant.TOKEN,bean.getToken());
        aCache.put(Constant.USER,bean.getUser());
    }
}
