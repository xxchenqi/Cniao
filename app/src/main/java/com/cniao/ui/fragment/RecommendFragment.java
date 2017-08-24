package com.cniao.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cniao.R;
import com.cniao.bean.IndexBean;
import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerRecommendComponent;
import com.cniao.di.module.RecommendModule;
import com.cniao.presenter.RecommendPresenter;
import com.cniao.presenter.contract.RecommendContract;
import com.cniao.ui.adapter.IndexMultipleAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chenqi on 2017/6/6.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private IndexMultipleAdapter adapter;

    @Inject
    ProgressDialog mProgressDialog;

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend;
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this)).build().inject(this);
    }

    @Override
    public void init() {
        initRecycleView();
        mPresenter.requestDatas();
    }

    private void initRecycleView() {
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void showResult(IndexBean indexBean) {
        adapter = new IndexMultipleAdapter(getActivity());
        adapter.setData(indexBean);
        recycler_view.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionSuccess() {
        mPresenter.requestDatas();
    }

    @Override
    public void onRequestPermissionError() {
        Toast.makeText(getActivity(), "您已拒绝授权", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }
}
