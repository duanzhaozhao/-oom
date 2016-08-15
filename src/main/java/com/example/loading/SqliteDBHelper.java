package com.example.loading;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by skysoft on 2016/7/11.
 */
public class SqliteDBHelper extends SQLiteOpenHelper {
    public SqliteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "create table TABLE_REGSITER(id Integer primary key autoincrement," +
                "user varchar(3),password varchar(3) )";
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
