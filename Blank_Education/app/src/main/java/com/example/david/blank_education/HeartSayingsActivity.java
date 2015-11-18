package com.example.david.blank_education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import medusa.theone.waterdroplistview.view.WaterDropListView;

/**
 * Created by 123 on 2015/11/17.
 */
public class HeartSayingsActivity extends Activity implements WaterDropListView.IWaterDropListViewListener{
    WaterDropListView listView;
    private List<Map<String, Object>> list;
    private Map<String ,Object> map;
    private SimpleAdapter adapter;
    int counter = 10;
    private int CURRENT_LISTVIEW_ITEM_POSITION = 0;
    boolean isMore;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartsayings);
        init();

    }

    private void init()
    {

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable(getResources().getDrawable(R.drawable.lamp_pic));

        FloatingActionButton rightLowerButton = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();
        rightLowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeartSayingsActivity.this, UploadHeartSayingActivity.class);
                startActivityForResult(intent, 3);
            }
        });


        listView = (WaterDropListView)findViewById(R.id.waterdrop_listview);
        list = new ArrayList<Map<String, Object>>();
        map = new HashMap<String, Object>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt_item_title = (TextView) view.findViewById(R.id.textview_heartsayings_listview);
                Intent intent = new Intent(HeartSayingsActivity.this, HeartSayingsDetailActivity.class);
                intent.putExtra("title", txt_item_title.getText().toString());
                startActivityForResult(intent, 2);
            }
        });
        listView.setWaterDropListViewListener(this);
        listView.setPullLoadEnable(true);
        load();
    }

    public void onRefresh() {
        update();

    }

    public void onLoadMore() {
        loadMore();
    }

    public void update()
    {
        list.clear();
        adapter = new SimpleAdapter(HeartSayingsActivity.this, list, R.layout.listview_heartsayings_layout,
                new String[]{"title", "good","bad"},
                new int[]{R.id.textview_heartsayings_listview, R.id.textview_heartsayings_listview_well_text,R.id.textview_heartsayings_listview_bad_text});

        listView.setAdapter(adapter);
        BmobQuery<HeartSayings> query = new BmobQuery<>();
        query.addWhereEqualTo("id", 1);
        query.setLimit(11);
        query.order("-createdAt");
        query.findObjects(this, new FindListener<HeartSayings>() {
            @Override
            public void onSuccess(List<HeartSayings> objectsList) {
                int i = 0;
                if (objectsList.size() > 10) {
                    isMore = true;
                } else {
                    isMore = false;
                }
                for (HeartSayings item : objectsList) {
                    i++;
                    if (i == 11) {
                        isMore = true;
                        break;
                    } else {
                        map = new HashMap<String, Object>();
                        map.put("title", item.getTitle());
                        map.put("good", item.getAmountOfGood().toString());
                        map.put("bad", item.getAmountOfBad().toString());
                        list.add(map);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

                new Toast(HeartSayingsActivity.this).makeText(HeartSayingsActivity.this, R.string.load_failed, Toast.LENGTH_SHORT).show();
            }
        });

        listView.stopRefresh();
    }

    public void loadMore()
    {
        startUpdate();
        if(isMore) {
            adapter = new SimpleAdapter(HeartSayingsActivity.this, list, R.layout.listview_heartsayings_layout,
                    new String[]{"title", "good","bad"},
                    new int[]{R.id.textview_heartsayings_listview, R.id.textview_heartsayings_listview_well_text,R.id.textview_heartsayings_listview_bad_text});

            listView.setAdapter(adapter);
            BmobQuery<HeartSayings> query = new BmobQuery<>();
            query.addWhereEqualTo("id", 1);
            query.setLimit(11);
            query.order("-createdAt");
            query.setSkip(counter);
            query.findObjects(this, new FindListener<HeartSayings>() {
                @Override
                public void onSuccess(List<HeartSayings> objectsList) {
                    int i = 0;
                    if (objectsList.size() > 10) {
                        isMore = true;
                    } else {
                        isMore = false;
                    }
                    for (HeartSayings item : objectsList) {
                        i++;
                        if (i == 11) {
                            counter+=10;
                            break;
                        } else {
                            map = new HashMap<String, Object>();
                            map.put("title", item.getTitle());
                            map.put("good", item.getAmountOfGood().toString());
                            map.put("bad", item.getAmountOfBad().toString());
                            list.add(map);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }


                @Override
                public void onError(int i, String s) {
                    new Toast(HeartSayingsActivity.this).makeText(HeartSayingsActivity.this, R.string.load_failed, Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            new Toast(HeartSayingsActivity.this).makeText(HeartSayingsActivity.this, R.string.no_more, Toast.LENGTH_SHORT).show();

        }
        finishUpdate();
        listView.stopLoadMore();
    }

    public void load()
    {
        list.clear();
        adapter = new SimpleAdapter(HeartSayingsActivity.this, list, R.layout.listview_heartsayings_layout,
                new String[]{"title", "good","bad"},
                new int[]{R.id.textview_heartsayings_listview, R.id.textview_heartsayings_listview_well_text,R.id.textview_heartsayings_listview_bad_text});


        listView.setAdapter(adapter);
        BmobQuery<HeartSayings> query = new BmobQuery<>();
        query.addWhereEqualTo("id", 1);
        query.setLimit(11);
        query.order("-createdAt");
        query.findObjects(this, new FindListener<HeartSayings>() {
            @Override
            public void onSuccess(List<HeartSayings> objectsList) {
                int i = 0;
                if (objectsList.size() > 10) {
                    isMore = true;
                } else {
                    isMore = false;
                }
                for (HeartSayings item : objectsList) {
                    i++;
                    if (i == 11) {
                        isMore = true;
                        break;
                    } else {
                        map = new HashMap<String, Object>();
                        map.put("title", item.getTitle());
                        map.put("good", item.getAmountOfGood().toString());
                        map.put("bad", item.getAmountOfBad().toString());
                        list.add(map);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                new Toast(HeartSayingsActivity.this).makeText(HeartSayingsActivity.this, R.string.load_failed, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startUpdate()
    {
        CURRENT_LISTVIEW_ITEM_POSITION = listView.getFirstVisiblePosition();
    }


    public void finishUpdate()
    {
        listView.setSelection(CURRENT_LISTVIEW_ITEM_POSITION);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 2)
            if(data.getStringExtra("movitation").equals("yes"))
                load();

        else if(requestCode == 3)
                if(data.getStringExtra("movitation").equals("reload"));
                    load();

    }
}
