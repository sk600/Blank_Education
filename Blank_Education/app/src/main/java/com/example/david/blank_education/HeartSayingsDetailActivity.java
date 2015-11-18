package com.example.david.blank_education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import medusa.theone.waterdroplistview.view.WaterDropListView;

/**
 * Created by 123 on 2015/11/18.
 */
public class HeartSayingsDetailActivity extends Activity {

    WaterDropListView listView;
    private List<Map<String, Object>> list;
    private Map<String ,Object> map;
    private SimpleAdapter adapter;
    int counter = 10;
    private int CURRENT_LISTVIEW_ITEM_POSITION = 0;
    boolean isMore;
    boolean goodMore = true;
    boolean badMore = true;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartsayingsdetail);
        init();
        int requestCode = 2;
        Intent intent = new Intent();
        intent.putExtra("movitation", "yes");
        this.setResult(requestCode, intent);
    }

    private void init()
    {
        final TextView title = (TextView)findViewById(R.id.textview_heartsayingsdetail_title);
        final TextView content = (TextView)findViewById(R.id.textview_heartsayingsdetail_content);
        final ImageView good = (ImageView)findViewById(R.id.imageview_heartsayingsdetail_listview_well_pic);
        final ImageView bad = (ImageView)findViewById(R.id.imageview_heartsayingsdetail_listview_bad_pic);
        final TextView t_good = (TextView)findViewById(R.id.textview_heartsayingsdetail_listview_well_text);
        final TextView t_bad = (TextView)findViewById(R.id.textview_heartsayingsdetail_listview_bad_text);

        Bundle extras = getIntent().getExtras();
        final String title_text = extras.getString("title");
        BmobQuery<HeartSayings> query = new BmobQuery<>();
        query.addWhereEqualTo("title",title_text);
        query.setLimit(1);
        query.findObjects(this, new FindListener<HeartSayings>() {
            @Override
            public void onSuccess(List<HeartSayings> list) {
                final HeartSayings heart = list.get(0);
                final String id = heart.getObjectId();
                title.setText(heart.getTitle());
                t_good.setText(heart.getAmountOfGood().toString());
                t_bad.setText(heart.getAmountOfBad().toString());
                content.setText(heart.getContent());

                good.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        heart.setAmountOfGood();
                        heart.update(HeartSayingsDetailActivity.this, id, new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                goodMore = false;
                                good.setEnabled(goodMore);
                                if(!goodMore)
                                {
                                    int amountgood = heart.getAmountOfGood().intValue();
                                    t_good.setText(amountgood+"");
                                }
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                new Toast(HeartSayingsDetailActivity.this).makeText(HeartSayingsDetailActivity.this,
                                        "failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                bad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        heart.setAmountOfBad();
                        heart.update(HeartSayingsDetailActivity.this, id, new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                badMore = false;
                                bad.setEnabled(badMore);
                                if(!badMore)
                                {
                                    int amountbad = heart.getAmountOfBad().intValue();
                                    t_bad.setText(amountbad+"");
                                }
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                new Toast(HeartSayingsDetailActivity.this).makeText(HeartSayingsDetailActivity.this,
                                        "failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }

            @Override
            public void onError(int i, String s) {
                new Toast(HeartSayingsDetailActivity.this).makeText(HeartSayingsDetailActivity.this,
                        "failed-----", Toast.LENGTH_SHORT).show();
            }
        });

//        listView = (WaterDropListView)findViewById(R.id.waterdrop_listview);
//        list = new ArrayList<Map<String, Object>>();
//        map = new HashMap<String, Object>();
//        listView.setWaterDropListViewListener(this);
//        listView.setPullLoadEnable(true);
//        load();
    }

//    public void load()
//    {
//        list.clear();
//        adapter = new SimpleAdapter(HeartSayingsDetailActivity.this, list, R.layout.listview_heartsayingsdetail_layout,
//                new String[]{"author","content"},
//                new int[]{R.id.textview_heartsayingsdetail_listview_recall_author, R.id.textview_heartsayingsdetail_listview_recall_content});
//        listView.setAdapter(adapter);
//        BmobQuery<HeartSayings> query = new BmobQuery<>();
//        query.addWhereEqualTo("id", 1);
//        query.setLimit(11);
//        query.order("-createdAt");
//        query.findObjects(this, new FindListener<HeartSayings>() {
//            @Override
//            public void onSuccess(List<HeartSayings> objectsList) {
//                int i = 0;
//                if (objectsList.size() > 10) {
//                    isMore = true;
//                } else {
//                    isMore = false;
//                }
//                for (HeartSayings item : objectsList) {
//                    i++;
//                    if (i == 11) {
//                        isMore = true;
//                        break;
//                    } else {
//                        map = new HashMap<String, Object>();
//                        map.put("title", item.getTitle());
//                        map.put("good", item.getAmountOfGood().intValue());
//                        map.put("bad", item.getAmountOfBad().intValue());
//                        list.add(map);
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onError(int i, String s) {
//                new Toast(HeartSayingsDetailActivity.this).makeText(HeartSayingsDetailActivity.this, R.string.load_failed, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}
