package com.cniao.presenter.contract;

import com.cniao.bean.IndexBean;
import com.cniao.ui.BaseView;

/**
 * Created by chenqi on 2017/6/7.
 */

public interface RecommendContract {
    interface View extends BaseView {
        void showResult(IndexBean indexBean);

        void onRequestPermissionSuccess();

        void onRequestPermissionError();
    }
}
