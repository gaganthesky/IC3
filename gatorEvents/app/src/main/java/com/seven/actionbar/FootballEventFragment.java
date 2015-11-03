package com.seven.actionbar;

/**
 * Created by vineet on 10/14/15.
 */
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FootballEventFragment extends ListFragment {
    private ArrayAdapter<String> adapter;
    private List<String> data;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        data = new ArrayList<String>();
        for(int i = 0; i < 30; i++){
            data.add("rose" + i);
        }
        manager = getFragmentManager();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        String str = adapter.getItem(position);
        transaction = manager.beginTransaction();

        DetailFragment detailFragment = new DetailFragment();
        //user class Bundle to fetch data

        Bundle bundle = new Bundle();
        bundle.putString("id", str);
        detailFragment.setArguments(bundle);
        //10/29 9:50PM
       // transaction.replace(R.id.right, detailFragment, "detail");
       // transaction.commit();
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

}
