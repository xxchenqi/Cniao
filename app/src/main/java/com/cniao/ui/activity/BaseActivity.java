package com.cniao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.cniao.AppApplication;
import com.cniao.di.component.AppComponent;
import com.cniao.presenter.BasePresenter;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenqi on 2017/6/9.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private Unbinder bind;
    private AppApplication mApplication;
    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        bind = ButterKnife.bind(this);
        this.mApplication = (AppApplication) getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != Unbinder.EMPTY) {
            bind.unbind();
        }
    }

    protected void startActivity(Class c) {
        this.startActivity(new Intent(this, c));
    }


}
