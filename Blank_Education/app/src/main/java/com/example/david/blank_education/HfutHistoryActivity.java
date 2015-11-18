package com.example.david.blank_education;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import se.emilsjolander.flipview.FlipView;

/**
 * Created by 123 on 2015/11/18.
 */
public class HfutHistoryActivity extends Activity {

    private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
    private HashMap<String, Object> map;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hfuthistory);
        init();
    }

    private void init()
    {
        FlipView flipView = (FlipView) findViewById(R.id.flip_view);
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.listview_hfuthistory_layout,
                new String[] {"content_pic","content_text","content_number"},new int []{R.id.imageview_hfuthistory_content_pic,R.id.textview_hfuthistory_content_text,R.id.textview_hfuthistory_number});
        flipView.setAdapter(adapter);
        int i = 1;

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_liushaoqi_1_pic);
        map.put("content_text",getString(R.string.history_text_1));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_dongbiwu_2_pic);
        map.put("content_text",getString(R.string.history_text_2));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_zhude_3_pic);
        map.put("content_text",getString(R.string.history_text_3));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_dengxiaoping_4_pic);
        map.put("content_text",getString(R.string.history_text_4));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_zhongdian_5_pic);
        map.put("content_text",getString(R.string.history_text_5));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_chenyi_6_pic);
        map.put("content_text",getString(R.string.history_text_6));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_yizhi_7_pic);
        map.put("content_text",getString(R.string.history_text_7));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_chenyi_8_pic);
        map.put("content_text",getString(R.string.history_text_8));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_dongbiwu_9_pic);
        map.put("content_text",getString(R.string.history_text_9));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_donglou_10_pic);
        map.put("content_text",getString(R.string.history_text_10));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_liushaoqi_11_pic);
        map.put("content_text",getString(R.string.history_text_11));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();

        map = new HashMap<>();
        map.put("content_pic", R.drawable.history_dengxiaoping_12_pic);
        map.put("content_text",getString(R.string.history_text_12));
        map.put("content_number",getString(R.string.name_di)+ i + getString(R.string.name_ye));
        i++;
        list.add(map);
        adapter.notifyDataSetChanged();
    }
}
