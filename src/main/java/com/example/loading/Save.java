package com.example.loading;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skysoft on 2016/7/11.
 */
public class Save {
    static SqliteDBHelper helper;
    private static int id = 0;
   static JSONArray users1 = new JSONArray();
    //保存到数据库 成功
    public static void sql_save(Context context,String user, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user",user);
        contentValues.put("password",password);
          helper = new SqliteDBHelper(context,"sql存储",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert("TABLE_REGSITER",null,contentValues);
        db.close();
    }

//保存为json格式,成功
    public static void json_save(Context context, String userName,String password) {
        JSONObject user = new JSONObject();

        try {
                user.put("user", userName);
                user.put("password",password);
                users1.put(user);
            FileOutputStream outStream=context.openFileOutput("duan.txt",Context.MODE_PRIVATE);
            outStream.write(users1.toString().getBytes());
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

}

    //保存为xml格式
    public static void xml_save(Context context,String user,String password) {
        try {
            FileOutputStream outStream=context.openFileOutput("zhao.txt",Context.MODE_PRIVATE);//在文件后追加模式
            XmlSerializer xmlSerializer = Xml.newSerializer();
                xmlSerializer.setOutput(outStream,"utf-8");

            xmlSerializer.startDocument("utf-8",true);
            xmlSerializer.startTag(null,"user");

            xmlSerializer.startTag(null, "username");
            xmlSerializer.text(user);
            xmlSerializer.endTag(null,"username");

            xmlSerializer.startTag(null, "password");
            xmlSerializer.text(password);
            xmlSerializer.endTag(null,"password");

            xmlSerializer.endTag(null, "user");
            xmlSerializer.endDocument();
            Log.i("aaa",xmlSerializer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
