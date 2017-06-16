package com.cniao.data;

import com.cniao.bean.AppInfo;
import com.cniao.bean.BaseBean;
import com.cniao.bean.PageBean;
import com.cniao.data.http.ApiService;

import retrofit2.Callback;
import rx.Observable;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {
        return mApiService.getApps("{'page':0}");
    }
}
