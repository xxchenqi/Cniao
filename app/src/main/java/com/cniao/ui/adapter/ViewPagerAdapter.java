package com.cniao.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cniao.ui.bean.FragmentInfo;
import com.cniao.ui.fragment.CategoryFragment;
import com.cniao.ui.fragment.GamesFragment;
import com.cniao.ui.fragment.RankingFragment;
import com.cniao.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqi on 2017/6/6.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private List<FragmentInfo> mFragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    private void initFragment() {
        mFragments.add(new FragmentInfo("推荐", RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行", RankingFragment.class));
        mFragments.add(new FragmentInfo("游戏", GamesFragment.class));
        mFragments.add(new FragmentInfo("分类", CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
