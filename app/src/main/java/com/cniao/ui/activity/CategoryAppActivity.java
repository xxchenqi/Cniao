package com.cniao.ui.activity;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cniao.R;
import com.cniao.bean.Category;
import com.cniao.common.Constant;
import com.cniao.di.component.AppComponent;
import com.cniao.ui.adapter.CategoryAppViewPagerAdapter;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;

public class CategoryAppActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private Category category;


    @Override
    public int setLayout() {
        return R.layout.activity_cateogry_app;
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    public void init() {
        getData();
        initTablayout();
    }


    private void initTablayout() {
        mToolBar.setTitle(category.getName());
        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CategoryAppViewPagerAdapter adapter = new CategoryAppViewPagerAdapter(getSupportFragmentManager(), category.getId());
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void getData() {
        Intent intent = getIntent();
        category = (Category) intent.getSerializableExtra(Constant.CATEGORY);
    }
}
