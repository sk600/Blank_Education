package com.example.david.blank_education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 123 on 2015/11/18.
 */
public class UploadHeartSayingActivity extends Activity {

    private CircularProgressButton button;
    private EditText title,content;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadheartsaying);
        Intent intent = new Intent();
        intent.putExtra("movitation","reload");
        setResult(3,intent);
        init();
    }

    public void init()
    {
        button = (CircularProgressButton)findViewById(R.id.btn_upload);
        title = (EditText)findViewById(R.id.edittext_uploadheartsaying_title);
        content = (EditText)findViewById(R.id.edittext_uploadheartsaying_content);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setProgress(5);
                HeartSayings heart = new HeartSayings();
                heart.setId(1);
                heart.setTitle(title.getText().toString());
                heart.setContent(content.getText().toString());
                heart.save(UploadHeartSayingActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        button.setProgress(100);
                        new Toast(UploadHeartSayingActivity.this).makeText(UploadHeartSayingActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                        UploadHeartSayingActivity.this.finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        button.setProgress(0);
                        new Toast(UploadHeartSayingActivity.this).makeText(UploadHeartSayingActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
