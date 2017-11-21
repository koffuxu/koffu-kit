package com.koffuxu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;
import com.koffuxu.myapplication.mvpsample.*;
import com.koffuxu.myapplication.thirdlib.dagger2test.MainActivityTestDagger;

/**
 * Created by koffuxu on 2016/11/20.
 */
public class ThirdLibListActivity extends Activity{

    private ListView listViewBitmap = null;
    private List<String> getDate() {
        List<String> data = new ArrayList<String>();
        data.add("ButterKnife");
        data.add("mvp sample");
        data.add("dagger2 test");
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        listViewBitmap = (ListView) findViewById(R.id.listViewBitmap);
        listViewBitmap.setAdapter(new ArrayAdapter<String>(this, R.layout.text_one_line, getDate()));
        listViewBitmap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i2 = new Intent();
                switch (position) {
                    case 0:
                        Log.d(MainActivity.TAG, "Item Butter Knife Selected");
                        i2.setClass(ThirdLibListActivity.this, ButterKnifeTest.class);
                        startActivity(i2);
                        break;
                    case 1:
                        Log.d(MainActivity.TAG, "Item mvp smaple Selected");
                        i2.setClass(ThirdLibListActivity.this, MVPSampleMainActivity.class);
                        startActivity(i2);
                        break;
                    case 2:
                        Log.d(MainActivity.TAG, "Item dagger2 test Selected");
                        i2.setClass(ThirdLibListActivity.this, MainActivityTestDagger.class);
                        startActivity(i2);
                        break;


                }
            }
        });

    }
}
