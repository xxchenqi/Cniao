package com.cniao.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cniao.R;
import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.di.component.AppComponent;
import com.cniao.presenter.AppInfoPresenter;
import com.cniao.presenter.contract.AppInfoContract;
import com.cniao.ui.activity.AppDetailActivity;
import com.cniao.ui.adapter.AppInfoAdapter;
import com.cniao.ui.widget.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by chenqi on 2017/9/18.
 */

public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements
        AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Inject
    RxDownload mRxDownload;
    int page = 0;

    protected AppInfoAdapter mAdapter;

    @Override
    public void init() {
        mPresenter.requestData(type(), page);
        initRecyclerView();
    }

    protected void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = buildAdapter();
        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AppInfo appInfo = mAdapter.getItem(position);
                mApplication.setView(view);
                Intent intent  = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra("appinfo",appInfo);
                startActivity(intent);
            }
        });
    }

    abstract int type();

    abstract AppInfoAdapter buildAdapter();

    @Override
    public int setLayout() {
        return R.layout.template_recycle_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
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
        mPresenter.requestData(type(), page);
    }
}
