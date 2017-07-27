package com.cniao.common.rx.subscriber;

import android.content.Context;

import com.cniao.common.exception.BaseException;
import com.cniao.common.util.ProgressDialogHandler;
import com.cniao.ui.BaseView;


public abstract class ProgressSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener{

    private BaseView mView;

    public ProgressSubscriber(Context context, BaseView view) {
        super(context);
        this.mView = view;
    }

    public boolean isShowProgress() {
        return true;
    }

    @Override
    public void onStart() {
        if (isShowProgress()) {
            mView.showLoading();
        }
    }

    @Override
    public void onCompleted() {
        mView.dismissLoading();
    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException = mErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }
}
