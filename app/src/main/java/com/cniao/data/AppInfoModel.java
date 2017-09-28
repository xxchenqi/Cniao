package com.cniao.data;

import com.cniao.bean.AppInfo;
import com.cniao.bean.BaseBean;
import com.cniao.bean.IndexBean;
import com.cniao.bean.PageBean;
import com.cniao.data.http.ApiService;

import io.reactivex.Observable;


/**
 * Created by chenqi on 2017/6/7.
 */

public class AppInfoModel {

    private ApiService mApiService;

    public AppInfoModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<BaseBean<IndexBean>> index() {
        return mApiService.index();
    }

    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page) {
        return mApiService.topList(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> games(int page) {
        return mApiService.games(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(int categoryid, int page) {
        return mApiService.getFeaturedAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(int categoryid, int page) {
        return mApiService.getTopListAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(int categoryid, int page) {
        return mApiService.getNewListAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<AppInfo>> getAppDetail( int id){

        return  mApiService.getAppDetail(id);
    }


}
