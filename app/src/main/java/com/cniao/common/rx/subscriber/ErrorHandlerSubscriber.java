package com.cniao.common.rx.subscriber;

import android.content.Context;

import com.cniao.common.exception.BaseException;
import com.cniao.common.rx.RxErrorHandler;

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
        }
    }
}
