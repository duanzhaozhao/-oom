package com.example.loading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skysoft on 2016/7/11.
 */
public class RegsiterActivity extends Activity implements View.OnClickListener{
    private EditText userID1,passwordID1;
    private Button register1;
    String userName,password;
    static List<User> users = new ArrayList<User>();;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        userID1 = (EditText) findViewById(R.id.userId1);
        passwordID1 = (EditText) findViewById(R.id.passwordId1);
        register1 = (Button) findViewById(R.id.register1);
        register1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        userName = userID1.getText().toString();
        password = passwordID1.getText().toString();
       User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        users.add(user);


        switch (v.getId()) {
            case R.id.register1:
                if ((TextUtils.isEmpty(userID1.getText())) || (TextUtils.isEmpty(passwordID1.getText()))) {
                    Toast.makeText(this,"请输入用户名或密码",Toast.LENGTH_SHORT).show();
                }else {
                    Save.sql_save(this,userName,password);//用sqlite存储
                    Save.json_save(this,userName,password);//用json存储
                    Save.xml_save(this,userName,password);//用xml存储
                    Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,LoadingActivity.class);
                    intent.putExtra("userName",userName);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
