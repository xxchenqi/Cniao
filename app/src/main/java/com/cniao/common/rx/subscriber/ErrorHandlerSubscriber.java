package com.cniao.common.rx.subscriber;

import android.content.Context;
import android.content.Intent;

import com.cniao.common.exception.BaseException;
import com.cniao.common.rx.RxErrorHandler;
import com.cniao.ui.activity.LoginActivity;

/**
 * Created by chenqi on 2017/6/13.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    protected RxErrorHandler mErrorHandler = null;

    protected Context mContext;

    public ErrorHandlerSubscriber(Context context) {
        this.mContext = context;
        mErrorHandler = new RxErrorHandler(mContext);
    }


    @Override
    public void onError(Throwable e) {
        BaseException exception = mErrorHandler.handleError(e);
        if (exception == null) {
            e.printStackTrace();
        } else {
            mErrorHandler.showErrorMessage(exception);
            if (exception.getCode() == BaseException.ERROR_TOKEN) {
                toLogin();
            }
        }
    }

    private void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
