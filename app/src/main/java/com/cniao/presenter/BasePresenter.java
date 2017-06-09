package com.cniao.presenter;

import com.cniao.ui.BaseView;

/**
 * Created by chenqi on 2017/6/7.
 */

public class BasePresenter<M, V extends BaseView> {
    protected M mModel;
    protected V mView;

    public BasePresenter(M m, V v) {
        this.mModel = m;
        this.mView = v;
    }
}
