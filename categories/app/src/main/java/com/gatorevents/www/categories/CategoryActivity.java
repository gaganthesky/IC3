package com.gatorevents.www.categories;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;


public class CategoryActivity extends FragmentActivity {

    public static class MyPageAdapter extends FragmentPagerAdapter {
        private static  int NUM_ITEMS = 4;

        public  MyPageAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }

        //Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        //Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0: //FRAGMENT 0 : This will show FirstFragment
                    return FirstFragment.newInstance(0, "uf official");
                case 1:
                    
            }
        }
    }
}
