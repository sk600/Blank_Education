package com.example.david.blank_education;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 123 on 2015/11/14.
 */
public class MeFragment extends Fragment {
    private View parentView;
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    HashMap<String, Object> map;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_me, container, false);
        return parentView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        TextView title = (TextView)getActivity().findViewById(R.id.textview_main_title_text);
        title.setText(getString(R.string.me_title));
        init();
    }

    private void init()
    {
        ListView listview =(ListView)getActivity().findViewById(R.id.listview_main_me);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(getActivity(), listItem,
                R.layout.listview_me_layout,
                new String[]{"Discrip"},
                new int[]{R.id.textview_listview_me});
        listview.setAdapter(mSimpleAdapter);

        map = new  HashMap<String, Object>();
        map.put("Discrip", getString(R.string.list_question));
        listItem.add(map);
        mSimpleAdapter.notifyDataSetChanged();
        map = new  HashMap<String, Object>();
        map.put("Discrip", getString(R.string.list_about));
        listItem.add(map);
        mSimpleAdapter.notifyDataSetChanged();
        map = new  HashMap<String, Object>();
        map.put("Discrip", getString(R.string.list_delete));
        listItem.add(map);
        mSimpleAdapter.notifyDataSetChanged();

    }
}
