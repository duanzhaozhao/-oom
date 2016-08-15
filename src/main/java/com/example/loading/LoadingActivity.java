package com.example.loading;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class LoadingActivity extends Activity implements View.OnClickListener{
private Button sql,json,xml,resgister;
    private EditText userId,passwordId;
    private String user,password;
    private SQLiteDatabase db;
    private SqliteDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        json = (Button) findViewById(R.id.json);
        xml = (Button) findViewById(R.id.xml);
        resgister = (Button) findViewById(R.id.regiser);
        sql = (Button) findViewById(R.id.sql);
        userId = (EditText) findViewById(R.id.userId);
        passwordId = (EditText) findViewById(R.id.passwordId);

        json.setOnClickListener(this);
        xml.setOnClickListener(this);
        resgister.setOnClickListener(this);
        sql.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        user = userId.getText().toString();
        password = passwordId.getText().toString();
        switch (v.getId()) {

            //解析json数据
            case R.id.json:
               if(getJsondata()) {
                   Intent intent = new Intent(this,SecondActivity.class);
                   startActivity(intent);
               }else {
                   Toast.makeText(this,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
               }
                break;

            //解析xml数据
            case R.id.xml:
                if(getXmldata()) {
                    Intent intent = new Intent(this,SecondActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                }
                break;

            //解析sql数据
            case R.id.sql:
                 if (getSqldata()){
                     Intent intent = new Intent(this,SecondActivity.class);
                     startActivity(intent);
                 }else {
                     Toast.makeText(this,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                 }
                break;

            case R.id.regiser:
                Intent intent = new Intent(this,RegsiterActivity.class);
                startActivity(intent);
                break;
        }
    }



    //解析sql数据
    private boolean getSqldata() {
        helper = new SqliteDBHelper(this,"sql存储",null,1);
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("TABLE_REGSITER",new String[] {"user","password"},"user = ? and password = ?",
                new String[] {user,password},null,null,null);

        //if (cursor.moveToNext() && TextUtils.isEmpty(user) && TextUtils.isEmpty(password)){return true;}
        if ( user.length() != 0  && password.length() != 0 && cursor.moveToNext()){  Log.i("ccc",user+"a");return true;}
        cursor.close();
      /* while (cursor.moveToNext()) {
            String u = cursor.getString(cursor.getColumnIndex("user"));
            String p = cursor.getString(cursor.getColumnIndex("password"));
            if ( u.equals(user) && p.equals(password) && user . && password != null) {
                Log.i("ccc",u + "a" + user);
                return true;
            }
        }*/
       // cursor.close();
        return false;
    }

    //解析json数据
    private boolean getJsondata() {
        try {

            FileInputStream in = this.openFileInput("duan.txt");
            BufferedReader in1 = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = in1.readLine()) != null) {
                builder.append(line);

            }
            in1.close();
            JSONArray jsonarray = new JSONArray(builder.toString());
            for (int i = 0;i<jsonarray.length();i++) {
                JSONObject json = jsonarray.getJSONObject(i);
                if (user.equals(json.getString("user")) && password.equals(json.getString("password"))){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 return false;
    }

    //解析xml数据
    private boolean getXmldata() {
        User user1 =null ;
        try {
            FileInputStream in = this.openFileInput("zhao.txt");
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(in, "UTF-8");// 设置数据源编码
            int eventType = parser.getEventType();// 获取事件类型
            while (eventType != XmlPullParser.END_DOCUMENT){

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("user")) {
                            user1 = new User();
                        }else if (name.equalsIgnoreCase("username")){
                        user1.setUserName(parser.nextText());
                    } else if (name.equalsIgnoreCase("password")) {
                        user1.setPassword(parser.nextText());
                    }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if ((user.equals(user1.getUserName()) && (password.equals(user1.getPassword()))) ) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        userId.setText(getIntent().getStringExtra("userName"));
        passwordId.setText(getIntent().getStringExtra("password"));
    }
}
