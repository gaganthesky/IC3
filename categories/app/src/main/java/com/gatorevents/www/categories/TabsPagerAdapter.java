package com.gatorevents.www.categories;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vineet on 10/9/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter
{
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {


        return null;
    }

    @Override
    public int getCount()
    {
        // get item count - equal to number of tabs
        return 2;
    }
}