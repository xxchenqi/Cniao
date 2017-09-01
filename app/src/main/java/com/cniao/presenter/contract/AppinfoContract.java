package com.cniao.presenter.contract;

import com.cniao.bean.AppInfo;
import com.cniao.bean.IndexBean;
import com.cniao.bean.PageBean;
import com.cniao.ui.BaseView;

/**
 * Created by chenqi on 2017/6/7.
 */

public interface AppInfoContract {
    interface View extends BaseView {
        void showResult(IndexBean indexBean);

        void onRequestPermissionSuccess();

        void onRequestPermissionError();
    }

    interface TopListView extends BaseView {
        void showResult(PageBean<AppInfo> appInfoPageBean);

        void onLoadMoreComplete();
    }
}
