package com.seven.actionbar;

/**
 * Created by vineet on 10/14/15.
 */
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FootballActivity extends Activity {

    Animation slideDown, slideUp;
    TextView mTextView;
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);
        ActionBar actionBar = this.getActionBar();
        actionBar.setTitle("Football");


        //setup animations
        slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        //        slideDown.setAnimationListener(this);
        //        slideUp.setAnimationListener(this);

        // LinearLayout
        mLinearLayout = (LinearLayout) findViewById(R.id.uflvsolemissExpandable);
        mLinearLayout.setVisibility(View.GONE);

    }

    public void toggleContents(View view)
    {
        String detailId = (String) view.getTag();
        switch (detailId)
        {

            case "uflvsolemiss":
                //mLinearLayout.clearAnimation();
                if (mLinearLayout.getVisibility() == View.VISIBLE) {
                    //    slideUp.reset();
                    //    mLinearLayout.clearAnimation();
                    //    mLinearLayout.startAnimation(slideUp);
                    //    mLinearLayout.setVisibility(View.GONE);
                    SlideDownAnim a = new SlideDownAnim(mLinearLayout, 200, SlideDownAnim.COLLAPSE);
                    a.getHeight();
                    mLinearLayout.startAnimation(a);
                } else {
                    //   slideDown.reset();
                    //   mLinearLayout.clearAnimation();
                    //   mLinearLayout.startAnimation(slideDown);
                    //   mLinearLayout.setVisibility(View.VISIBLE);
                    SlideDownAnim a = new SlideDownAnim(mLinearLayout, 200, SlideDownAnim.EXPAND);
                    a.getHeight();
                    mLinearLayout.startAnimation(a);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

