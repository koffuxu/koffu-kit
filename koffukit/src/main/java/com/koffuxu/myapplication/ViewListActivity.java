package com.koffuxu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.koffuxu.myapplication.view.CustomViewMain;
import com.koffuxu.myapplication.view.FragmentViewTest;
import com.koffuxu.myapplication.view.PopupWindowTest;
import com.koffuxu.myapplication.view.RecyclerViewTest;
import com.koffuxu.myapplication.view.ViewPageTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
public class ViewListActivity extends Activity {
    private ListView lv = null;
    private List<String> getDate() {
        List<String> data = new ArrayList<String>();
        data.add("FragmentView");
        data.add("CustomView");
        data.add("RecyclerView");
        data.add("PopupWindow");
        data.add("ViewPage");
        return data;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        lv = (ListView) findViewById(R.id.listViewViewTest);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.text_one_line, getDate()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                switch (position) {
                    case 0:
                        Log.d(MainActivity.TAG, "Item Fragment Selected");
                        i.setClass(ViewListActivity.this, FragmentViewTest.class);
                        startActivity(i);
                        break;
                    case 1:
                        //TODO
                        Log.d(MainActivity.TAG, "Item Fragment Selected");
                        i.setClass(ViewListActivity.this, CustomViewMain.class);
                        startActivity(i);
                        break;
                    case 2:
                        Log.d(MainActivity.TAG, "Item Fragment Selected");
                        i.setClass(ViewListActivity.this, RecyclerViewTest.class);
                        startActivity(i);
                        break;
                    case 3:
                        Log.d(MainActivity.TAG, "Item Fragment Selected");
                        i.setClass(ViewListActivity.this, PopupWindowTest.class);
                        startActivity(i);
                        break;
                    case 4:
                        Log.d(MainActivity.TAG, "Item ViewPage Selected");
                        i.setClass(ViewListActivity.this, ViewPageTest.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }
}
