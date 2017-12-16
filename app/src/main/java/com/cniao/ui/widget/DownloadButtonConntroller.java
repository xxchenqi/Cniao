package com.cniao.ui.widget;

import android.content.Context;

import com.cniao.R;
import com.cniao.bean.AppDownloadInfo;
import com.cniao.bean.AppInfo;
import com.cniao.common.util.AppUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.ui.widget
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class DownloadButtonConntroller {

    private RxDownload mRxDownload;
    private String mDownloadDir; // 文件下载的目录

    public void handClick(final DownloadProgressButton btn, final AppInfo appInfo) {
        isAppInstalled(btn.getContext(), appInfo)
                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
                    @Override
                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event)
                            throws Exception {
                        if (DownloadFlag.UN_INSTALL == event.getFlag()) {
                            return isApkFileExsit(appInfo);
                        }
                        return Observable.just(event);
                    }
                })
                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
                    @Override
                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event) throws Exception {
                        if (DownloadFlag.FILE_EXIST == event.getFlag()) {
                            return getAppDownloadInfo(appInfo).flatMap(new Function<AppDownloadInfo, ObservableSource<DownloadEvent>>() {
                                @Override
                                public ObservableSource<DownloadEvent> apply(@NonNull AppDownloadInfo appDownloadInfo) throws Exception {
                                    return receiveDownloadStatus(appDownloadInfo);
                                }
                            });

                        }
                        return Observable.just(event);
                    }
                })
                .subscribe(new Consumer<DownloadEvent>() {
                    @Override
                    public void accept(@NonNull DownloadEvent event) throws Exception {
                        int flag = event.getFlag();
                        btn.setTag(R.id.tag_apk_flag, flag);
                        switch (flag) {
                            case DownloadFlag.INSTALLED:
                                btn.setText("运行");
                                break;
                            case DownloadFlag.STARTED:
                                btn.setProgress((int) event.getDownloadStatus().getPercentNumber());
                                break;
                            case DownloadFlag.PAUSED:
                                btn.paused();
                                break;
                            case DownloadFlag.NORMAL:
                                btn.download();
                                break;
                        }

                    }
                });
    }


    private void bindClick(final DownloadProgressButton btn) {
        RxView.clicks(btn).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                int flag = (int) btn.getTag(R.id.tag_apk_flag);
            }
        });

    }


    public Observable<DownloadEvent> isAppInstalled(Context context, AppInfo appInfo) {
        DownloadEvent event = new DownloadEvent();
        event.setFlag(AppUtils.isInstalled(context, appInfo.getPackageName()) ? DownloadFlag.INSTALLED : DownloadFlag.UN_INSTALL);
        return Observable.just(event);
    }


    public Observable<DownloadEvent> isApkFileExsit(AppInfo appInfo) {
        String path = mDownloadDir + File.separator + appInfo.getReleaseKeyHash();
        File file = new File(path);
        DownloadEvent event = new DownloadEvent();
        event.setFlag(file.exists() ? DownloadFlag.FILE_EXIST : DownloadFlag.NORMAL);
        return Observable.just(event);
    }


    public Observable<DownloadEvent> receiveDownloadStatus(AppDownloadInfo appinfo) {
        return mRxDownload.receiveDownloadStatus(appinfo.getDownloadUrl());
    }


    public Observable<AppDownloadInfo> getAppDownloadInfo(AppInfo appInfo) {
        return null;
    }
}
