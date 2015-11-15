package com.example.david.blank_education;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by 123 on 2015/11/15.
 */
public class CallNextActivity extends Activity {

    public String[] name;
    public String[] phone;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_next);
        init();
    }

    private void init()
    {
        name = new String[] {getString(R.string.content_circut),
                getString(R.string.content_lamp), getString(R.string.pipe_content),
                getString(R.string.door_content), getString(R.string.content_else)};
        phone = new String[] {"13721560276","13721189276","13721100276","13721630276","13721100276"};
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", -1);
        TextView title = (TextView)findViewById(R.id.textview_call_next_title);
        TextView call = (TextView)findViewById(R.id.textview_call_next_phone);
        ImageButton pic = (ImageButton)findViewById(R.id.imagebutton_call_next_pic);

        title.setText(name[position]);
        call.setText(phone[position]);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone[position]));

                startActivity(pIntent);
            }
        });

    }
}
