package com.cniao.presenter.contract;

import com.cniao.bean.AppInfo;
import com.cniao.presenter.BasePresenter;
import com.cniao.ui.BaseView;

import java.util.List;

/**
 * Created by chenqi on 2017/6/7.
 */

public interface RecommendContract {
    interface View extends BaseView {
        void showResult(List<AppInfo> datas);
        void showNoData();
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void requestDatas();
    }
}
