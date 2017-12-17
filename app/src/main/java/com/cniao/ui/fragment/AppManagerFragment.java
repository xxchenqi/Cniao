package com.cniao.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cniao.R;
import com.cniao.common.apkparset.AndroidApk;
import com.cniao.presenter.AppManagerPresenter;
import com.cniao.presenter.contract.AppManagerContract;
import com.cniao.ui.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
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

public abstract class AppManagerFragment extends ProgressFragment<AppManagerPresenter>
        implements AppManagerContract.AppManagerView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @Override
    public void init() {
        setupRecyclerView();
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    private void setupRecyclerView() {


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecyclerView.addItemDecoration(itemDecoration);


        mRecyclerView.setAdapter(setupAdapter());
    }

    @Override
    public void showApps(List<AndroidApk> apps) {
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
    }

    protected abstract RecyclerView.Adapter setupAdapter();

}
