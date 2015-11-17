package com.example.david.blank_education;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 123 on 2015/11/17.
 */
public class RegistActivity extends Activity {

    EditText txt_name, txt_pwd, txt_conpwd, txt_stunum, txt_stupass;
    Button btn_regist;
    MyUser myUser;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();
    }

    private void init()
    {
        myUser = new MyUser();
        btn_regist = (Button) findViewById(R.id.btn_regist);
        txt_conpwd = (EditText) findViewById(R.id.txt_regist_password_confirm);
        txt_name = (EditText) findViewById(R.id.txt_regist_user_name);
        txt_pwd = (EditText) findViewById(R.id.txt_regist_password);
        txt_stunum = (EditText) findViewById(R.id.txt_regist_stu_num);
        txt_stupass = (EditText)findViewById(R.id.txt_regist_stu_pass);
        initNext();
    }

    private void initNext()
    {
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_stunum.getText().toString().equals("")
                        || txt_conpwd.getText().toString().equals("")
                        || txt_name.getText().toString().equals("")
                        || txt_pwd.getText().toString().equals("")) {
                    new Toast(RegistActivity.this).makeText(RegistActivity.this, getString(R.string.content_uncomplete), Toast.LENGTH_SHORT).show();
                } else if (!txt_pwd.getText().toString().equals(txt_conpwd.getText().toString())) {
                    new Toast(RegistActivity.this).makeText(RegistActivity.this, getString(R.string.unmatch_pwd), Toast.LENGTH_SHORT).show();
                }
                else if (!isTrueStu(Integer.parseInt(txt_stunum.getText().toString()))) {
                    new Toast(RegistActivity.this).makeText(RegistActivity.this, getString(R.string.wrong_stu_num), Toast.LENGTH_SHORT).show();
                }
                else {
                    myUser.setUsername(txt_name.getText().toString());
                    myUser.setPassword(txt_pwd.getText().toString());
                    myUser.setStudentNumber(Integer.parseInt(txt_stunum.getText().toString()));
                    myUser.setHeadImage("http://file.bmob.cn/M02/29/7D/oYYBAFYkAzKAXsnBAAAXNPCv3f8627.png");
                    myUser.setSchoolnumber(txt_stupass.getText().toString());
                    myUser.signUp(getApplication(), new SaveListener() {
                        @Override
                        public void onSuccess() {
                            new Toast(RegistActivity.this).makeText(RegistActivity.this, getString(R.string.regist_success), Toast.LENGTH_SHORT).show();
                            RegistActivity.this.finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {

                            new Toast(RegistActivity.this).makeText(RegistActivity.this, s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean isTrueStu(int num){
        return true;
    }
}
