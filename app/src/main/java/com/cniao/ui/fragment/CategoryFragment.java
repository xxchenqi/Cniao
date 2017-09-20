package com.cniao.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cniao.R;
import com.cniao.bean.Category;
import com.cniao.common.Constant;
import com.cniao.di.component.AppComponent;
import com.cniao.di.component.DaggerCategoryComponent;
import com.cniao.di.module.CategoryModule;
import com.cniao.presenter.CateogryPresenter;
import com.cniao.presenter.contract.CategoryContract;
import com.cniao.ui.activity.CategoryAppActivity;
import com.cniao.ui.adapter.CategoryAdapter;
import com.cniao.ui.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenqi on 2017/6/6.
 */

public class CategoryFragment extends ProgressFragment<CateogryPresenter> implements CategoryContract.CategoryView {


    @BindView(R.id.recycler_view)
    RecyclerView mRecycleView;

    private CategoryAdapter mAdapter;

    @Override
    public void init() {

        initRecyclerView();

        mPresenter.getAllCategory();
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycle_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder().appComponent(appComponent).categoryModule(new CategoryModule(this))
                .build().inject(this);
    }

    private void initRecyclerView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mRecycleView.addItemDecoration(itemDecoration);
        mAdapter = new CategoryAdapter();
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);
                intent.putExtra(Constant.CATEGORY, mAdapter.getData().get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);
    }
}
