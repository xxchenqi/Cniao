package com.cniao.data;

import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.http.ApiService;
import com.cniao.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendModel {
    public void getApps(Callback<PageBean<AppInfo>> callback) {
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        apiService.getApps("{'page':0}").enqueue(callback);
    }
}
