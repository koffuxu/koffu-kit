package com.koffuxu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.koffuxu.myapplication.bitmap.AnimationTest;
import com.koffuxu.myapplication.bitmap.BitmapReflectionActivity;
import com.koffuxu.myapplication.bitmap.BitmapTest;
import com.koffuxu.myapplication.bitmap.CanvasPaint;
import com.koffuxu.myapplication.bitmap.MatrixActivity;
import com.koffuxu.myapplication.bitmap.SurfaceViewMyActivity;
import com.koffuxu.myapplication.bitmap.TweenMyActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koffuxu on 2016/11/20.
 */
public class BitmapListActivity extends Activity{

    private ListView listViewBitmap = null;
    private List<String> getDate() {
        List<String> data = new ArrayList<String>();
        data.add("Bitmap");
        data.add("CanvesPaint");
        data.add("Animation");
        data.add("Matrix");
        data.add("SurfaceView");
        data.add("Tween");
        data.add("reflection");
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
                        Log.d(MainActivity.TAG, "Item Bitmap Selected");
                        i2.setClass(BitmapListActivity.this, BitmapTest.class);
                        startActivity(i2);
                        break;
                    case 1:
                        Log.d(MainActivity.TAG, "Canvas Paint Selected");
                        i2.setClass(BitmapListActivity.this, CanvasPaint.class);
                        startActivity(i2);
                        break;
                    case 2:
                        Log.d(MainActivity.TAG, "Canvas Paint Selected");
                        i2.setClass(BitmapListActivity.this, AnimationTest.class);
                        startActivity(i2);
                        break;
                    case 3:
                        Log.d(MainActivity.TAG, "Canvas Paint Selected");
                        i2.setClass(BitmapListActivity.this, MatrixActivity.class);
                        startActivity(i2);
                        break;
                    case 4:
                        Log.d(MainActivity.TAG, "Canvas Paint Selected");
                        i2.setClass(BitmapListActivity.this, SurfaceViewMyActivity.class);
                        startActivity(i2);
                        break;
                    case 5:
                        Log.d(MainActivity.TAG, "Canvas Paint Selected");
                        i2.setClass(BitmapListActivity.this, TweenMyActivity.class);
                        startActivity(i2);
                        break;
                     case 6:
                        i2.setClass(BitmapListActivity.this, BitmapReflectionActivity.class);
                        startActivity(i2);
                        break;
                }
            }
        });

    }
}
