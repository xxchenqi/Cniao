package com.cniao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.cniao.AppApplication;
import com.cniao.R;
import com.cniao.di.component.AppComponent;
import com.cniao.presenter.BasePresenter;
import com.cniao.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {


    private FrameLayout mRootView;

    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private Unbinder mUnbinder;

    private TextView mTextError;

    protected AppApplication mApplication;


    @Inject
    T mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress, container, false);
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);

        mTextError = (TextView) mRootView.findViewById(R.id.text_tip);

        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });


        return mRootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        setRealContentView();

        init();
    }

    public void onEmptyViewClick() {

    }

    private void setRealContentView() {

        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayout(), mViewContent, true);
        mUnbinder = ButterKnife.bind(this, realContentView);


    }


    public void showProgressView() {
        showView(R.id.view_progress);
    }

    public void showContentView() {
        showView(R.id.view_content);
    }

    public void showEmptyView() {
        showView(R.id.view_empty);
    }


    public void showEmptyView(int resId) {
        showEmptyView();
        mTextError.setText(resId);
    }

    public void showEmptyView(String msg) {
        showEmptyView();
        mTextError.setText(msg);
    }


    public void showView(int viewId) {
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            if (mRootView.getChildAt(i).getId() == viewId) {
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            } else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    public abstract void init();

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

}
