package com.cniao.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.cniao.common.apkparset.AndroidApk;
import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerAppManagerComponent;
import com.cniao.di.module.AppManagerModule;
import com.cniao.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstalledAppAppFragment extends AppManagerFragment {


    private  AndroidApkAdapter mAdapter;

    @Override
    public void init() {
        super.init();


        mPresenter.getInstalledApps();
    }
    @Override
    protected RecyclerView.Adapter setupAdapter() {

        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APP);

        return mAdapter;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerAppManagerComponent.builder().appManagerModule(new AppManagerModule(this))
                .appComponent(appComponent).build().injectInstalled(this);



    }


    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }
}
