package com.doublecc.yiyuannews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.doublecc.yiyuannews.bean.BeanChannel;
import com.doublecc.yiyuannews.fragment.NewsFragment;

import java.util.List;

/**
 * 主viewpager适配
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<BeanChannel> tabName;
    public MainViewPagerAdapter(FragmentManager fm, List<BeanChannel> tabName) {
        super(fm);
        this.tabName = tabName;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.getNewsFragment(tabName.get(position));
    }

    @Override
    public int getCount() {
        return tabName.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabName.get(position).name;
    }
}
