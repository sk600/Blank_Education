package com.example.david.blank_education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 123 on 2015/11/15.
 */
public class CallActivity extends Activity {
    HashMap<String,Object> map;
    private ArrayList<HashMap<String,Object>> list = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        init();
    }

    private void init()
    {

        ListView listview = (ListView)findViewById(R.id.listview_call);
        SimpleAdapter adapter = new SimpleAdapter(this,
                list,R.layout.listview_call_layout,
                new String[]{"firstpic","content","lastpic"},
                new int[]{R.id.imageview_call_listview_pic_first,R.id.textview_call_listview_text,R.id.imageview_call_listview_pic_last});
        listview.setAdapter(adapter);

        map = new HashMap<>();
        map.put("firstpic",R.drawable.circuit_pic);
        map.put("content",getString(R.string.content_circut));
        map.put("lastpic", R.drawable.call_phone);
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("firstpic",R.drawable.lamp_pic);
        map.put("content", getString(R.string.content_lamp));
        map.put("lastpic", R.drawable.call_phone);
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("firstpic",R.drawable.lamp_pic);
        map.put("content",getString(R.string.pipe_content));
        map.put("lastpic",R.drawable.call_phone);
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("firstpic",R.drawable.door_pic);
        map.put("content",getString(R.string.door_content));
        map.put("lastpic",R.drawable.call_phone);
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("firstpic",R.drawable.else_pic);
        map.put("content",getString(R.string.content_else));
        map.put("lastpic",R.drawable.call_phone);
        list.add(map);
        adapter.notifyDataSetChanged();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(CallActivity.this, CallNextActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
    }

    private void initelse()
    {

    }

}
