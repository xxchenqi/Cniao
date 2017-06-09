package com.cniao.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cniao.AppApplication;
import com.cniao.R;
import com.cniao.bean.AppInfo;
import com.cniao.di.component.DaggerRecommendComponent;
import com.cniao.di.module.RecommendModule;
import com.cniao.presenter.contract.RecommendContract;
import com.cniao.ui.adapter.RecommendAppAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenqi on 2017/6/6.
 */

public class RecommendFragment extends Fragment implements RecommendContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private RecommendAppAdapter adapter;
    @Inject
    RecommendContract.Presenter mPresenter;
    @Inject
    ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        DaggerRecommendComponent.builder().appComponent(AppApplication.get(getActivity()).getAppComponent())
        .recommendModule(new RecommendModule(this)).build().inject(this);
        initData();
        return view;
    }

    private void initData() {
        mPresenter.requestDatas();
    }

    private void initRecycleView(List<AppInfo> datas) {
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        recycler_view.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecommendAppAdapter(getActivity(), datas);
        recycler_view.setAdapter(adapter);
    }


    @Override
    public void showResult(List<AppInfo> datas) {
        initRecycleView(datas);
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "暂时无数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器异常:" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

    }
}
