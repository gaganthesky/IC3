package com.gatorevents.www.categories;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vineet on 10/9/15.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

    protected Context mContext;

    public CustomPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        mContext = context;
    }

    public Fragment getItem(int position)
    {
        Fragment fragment = new DemoFragment();

        Bundle args = new Bundle();
        args.putInt("page_position", position + 1);

        fragment.setArguments(args);

        return fragment;
    }

    public int getCount() {
        return 4;
    }

}
