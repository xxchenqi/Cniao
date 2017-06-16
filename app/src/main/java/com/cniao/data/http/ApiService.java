package com.cniao.data.http;

import com.cniao.bean.AppInfo;
import com.cniao.bean.BaseBean;
import com.cniao.bean.PageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ivan on 2016/12/30.
 */

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";


    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);


}
