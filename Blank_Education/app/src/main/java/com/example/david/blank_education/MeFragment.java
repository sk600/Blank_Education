package com.example.david.blank_education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 2:
                        MyUser.logOut(getActivity());
                        MyUser myUser = BmobUser.getCurrentUser(getActivity(), MyUser.class);
                        MainActivity parentActivity = (MainActivity) getActivity();
                        parentActivity.finish();
                        break;
                }
            }
        });

        initNext();

    }

    private void initNext()
    {
        TextView txt_name = (TextView) getActivity().findViewById(R.id.textview_me_pic);
        CircleImageView img_head = (CircleImageView)getActivity().findViewById(R.id.profile_image);
        final MyUser bmobUser = BmobUser.getCurrentUser(getActivity(), MyUser.class);
        if(bmobUser != null){
            Glide.with(getActivity()).load(bmobUser.getHeadImage()).into(img_head);
            txt_name.setText(bmobUser.getUsername());
            img_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 0);
                }
            });
            txt_name.setClickable(false);
        }
        else{
            txt_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            img_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
