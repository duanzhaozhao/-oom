package com.example.loading;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by skysoft on 2016/7/12.
 */
public class ThirdActivity extends Activity {
    ListView list;
    List<Mp3Info> mp3Infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);
        mp3Infos = MediaUtil.getMp3Infos(this);
        list = (ListView) findViewById(R.id.music_list);
        MArrayAdapter arrayAdapter = new MArrayAdapter(this,mp3Infos,R.layout.list_view);
        list.setAdapter(arrayAdapter);
    }
}
