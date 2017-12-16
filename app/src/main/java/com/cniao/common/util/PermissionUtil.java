package com.cniao.common.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5market.common.util
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class PermissionUtil {

    public static void readPhonestate(Context activity) {
        requestPermisson(activity, Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {

            }
        });
    }


    public static Observable<Boolean> requestPermisson(Context activity, String permission) {
        RxPermissions rxPermissions = RxPermissions.getInstance(activity);
        return rxPermissions.request(permission);
    }

}
