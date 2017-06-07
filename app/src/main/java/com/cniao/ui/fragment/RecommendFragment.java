package com.cniao.ui.fragment;

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

import com.cniao.R;
import com.cniao.bean.AppInfo;
import com.cniao.bean.PageBean;
import com.cniao.http.ApiService;
import com.cniao.http.HttpManager;
import com.cniao.ui.adapter.RecommendAppAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenqi on 2017/6/6.
 */

public class RecommendFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private RecommendAppAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        apiService.getApps("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                PageBean<AppInfo> pageBean = response.body();
                List<AppInfo> datas = pageBean.getDatas();
                initRecycleView(datas);
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

            }
        });
    }

    private void initRecycleView(List<AppInfo> datas) {
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        recycler_view.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecommendAppAdapter(getActivity(), datas);
        recycler_view.setAdapter(adapter);
    }


}
