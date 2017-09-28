package com.cniao.common.rx.subscriber;

import android.content.Context;

import com.cniao.common.util.ProgressDialogHandler;

import io.reactivex.disposables.Disposable;

/**
 * Created by chenqi on 2017/6/19.
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;
    private Disposable mDisposable;

    public ProgressDialogSubscriber(Context context) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(mContext, true, this);
    }

    protected boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onCancelProgress() {
//        unsubscribe();
        mDisposable.dispose();
    }
    public void onSubscribe(Disposable d) {

        mDisposable = d;
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();
        }

    }

//    @Override
//    public void onStart() {
//        if (isShowProgressDialog()) {
//            mProgressDialogHandler.showProgressDialog();
//        }
//    }

    @Override
    public void onComplete() {
        if (isShowProgressDialog()) {
            mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowProgressDialog()) {
            mProgressDialogHandler.dismissProgressDialog();
        }
    }

}
