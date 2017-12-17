package com.cniao.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerAppManagerComponent;
import com.cniao.di.module.AppManagerModule;
import com.cniao.ui.adapter.DownloadingAdapter;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.ui.fragment
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class DownloadingFragment extends AppManagerFragment {

    private DownloadingAdapter mAdapter;

    @Override
    public void init() {
        super.init();
        mPresenter.getDownlodingApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new DownloadingAdapter(mPresenter.geRxDowanload());
        return mAdapter;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppManagerComponent.builder().appComponent(appComponent).appManagerModule(new AppManagerModule(this))
                .build().inject(this);
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {

        mAdapter.addData(downloadRecords);
    }
}
