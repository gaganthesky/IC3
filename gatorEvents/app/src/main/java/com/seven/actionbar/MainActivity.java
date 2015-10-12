package com.seven.actionbar;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);

        LayoutInflater layoutInflater =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_main, null, false);
        frameLayout.addView(activityView);


    }


    @Override
    public void onStart(){
        super.onStart();
        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        /*if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.action_bar, menu);

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            try {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            }

            catch (Exception e)
            {
                System.err.println(e.toString());
            }
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);*/
        getMenuInflater().inflate(R.menu.action_bar, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        try {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }

        catch (Exception e)
        {
            System.err.println(e.toString());
        }
        return true;
    }


}
