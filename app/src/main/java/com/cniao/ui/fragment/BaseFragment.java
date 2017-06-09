package com.cniao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cniao.AppApplication;
import com.cniao.R;
import com.cniao.di.component.AppComponent;
import com.cniao.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenqi on 2017/6/9.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    private Unbinder bind;
    private AppApplication mApplication;
    @Inject
    T mPresenter;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayout(), container, false);
        bind = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != Unbinder.EMPTY) {
            bind.unbind();
        }
    }

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();
}
