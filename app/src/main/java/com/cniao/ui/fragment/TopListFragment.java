package com.cniao.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cniao.R;
import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerTopListComponent;
import com.cniao.di.module.TopListModule;
import com.cniao.presenter.TopListPresenter;
import com.cniao.presenter.contract.AppInfoContract;
import com.cniao.ui.adapter.AppInfoAdapter;
import com.cniao.ui.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Created by chenqi on 2017/6/6.
 */

public class TopListFragment extends ProgressFragment<TopListPresenter> implements AppInfoContract.TopListView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    int page = 0;

    AppInfoAdapter mAdapter;

    @Override
    public void init() {
        mPresenter.getTopListApps(page);
        initRecycle();
    }

    private void initRecycle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = AppInfoAdapter.builder().showBrief(false).showCategoryName(true).showPosition(true).build();
//        mAdapter = new AppInfoAdapter();
        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycle_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTopListComponent.builder().appComponent(appComponent).topListModule(new TopListModule(this))
                .build().inject(this);
    }

    @Override
    public void showResult(PageBean<AppInfo> appInfoPageBean) {
        mAdapter.addData(appInfoPageBean.getDatas());
        if (appInfoPageBean.isHasMore()) {
            page++;
        }
        mAdapter.setEnableLoadMore(appInfoPageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getTopListApps(page);
    }
}
