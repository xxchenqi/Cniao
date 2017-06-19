package com.cniao.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cniao.R;
import com.cniao.bean.AppInfo;
import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerRecommendComponent;
import com.cniao.di.module.RecommendModule;
import com.cniao.presenter.RecommendPresenter;
import com.cniao.presenter.contract.RecommendContract;
import com.cniao.ui.adapter.RecommendAppAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chenqi on 2017/6/6.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private RecommendAppAdapter adapter;

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
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }
}
