package com.cniao.data;

import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.data.http.ApiService;

import retrofit2.Callback;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public void getApps(Callback<PageBean<AppInfo>> callback) {
//        HttpManager manager = new HttpManager();
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        mApiService.getApps("{'page':0}").enqueue(callback);
    }
}
