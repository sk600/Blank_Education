package com.example.david.blank_education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by 123 on 2015/11/17.
 */
public class LoginActivity extends Activity {

    Button btn_login;
    TextView txt_regist;
    EditText txt_name, txt_pwd;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_name = (EditText) findViewById(R.id.txt_user_name);
        txt_pwd = (EditText) findViewById(R.id.txt_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.loginByAccount(LoginActivity.this, txt_name.getText().toString(), txt_pwd.getText().toString(), new LogInListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (myUser != null) {
                            new Toast(LoginActivity.this).makeText(LoginActivity.this, getString(R.string.login_sucess), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setAction("com.example.david.blank_education.broadcast.action");
                            sendBroadcast(intent);
                            LoginActivity.this.finish();
                        } else {
                            new Toast(LoginActivity.this).makeText(LoginActivity.this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
                        }
                    }


                });
            }
        });

        txt_regist = (TextView) findViewById(R.id.txt_login_regist);
        txt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
    }
}