package com.cniao.common.rx.subscriber;

import com.cniao.common.exception.BaseException;
import com.cniao.common.rx.RxErrorHandler;

/**
 * Created by chenqi on 2017/6/13.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    private RxErrorHandler mErrorHandler;

    public ErrorHandlerSubscriber(RxErrorHandler mErrorHandler) {
        this.mErrorHandler = mErrorHandler;
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = mErrorHandler.handleError(e);
        mErrorHandler.showErrorMessage(exception);

    }
}
