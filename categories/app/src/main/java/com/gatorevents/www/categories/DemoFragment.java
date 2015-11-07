package com.gatorevents.www.categories;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vineet on 10/9/15.
 */
public class DemoFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demo, container, false);
        Bundle args = getArguments();
        ((TextView)rootView.findViewById(R.id.text)).
                setText("Page " + args.getInt("page position"));

        return rootView;
    }
}
