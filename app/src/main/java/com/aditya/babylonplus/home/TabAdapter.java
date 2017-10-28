package com.aditya.babylonplus.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addTab(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }
}
