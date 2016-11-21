package com.koffuxu.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/*
 * This used for constructing a entry by Koffu Xu(koffuxu@gmail.com)
 */
public class MainActivity extends Activity {
    static final String TAG = "koffuxu";

    private ListView listView = null;
    private List<String> getDate() {
        List<String> data = new ArrayList<String>();
        data.add("ServiceTest");
        data.add("BitmapTest");
        data.add("ViewTest");
        data.add("FileOpsTest");
        return data;
    }

    private void openApp(String pkg, String act)
    {
        Log.d(TAG, "open the app----->pkg:" + pkg + "  act:" + act);
        ComponentName comp = new ComponentName(pkg, act);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(comp);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.text_one_line, getDate()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent i = new Intent();
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Log.d(TAG, "Item 0 Seleted");
                        break;
                    case 1:
                        Log.d(TAG, "Item BitmapTest Seleted");
                        //openApp("com.koffuxu.myapplication", "com.koffuxu.myapplication.BitmapListActivity");
                        i.setClass(MainActivity.this, BitmapListActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        Log.d(TAG, "Item 2 Seleted");
                        break;
                }
            }
        });

    }
}
