package com.koffuxu.myapplication.view;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.koffuxu.myapplication.R;

/**
 * Created by koffuxu on 2017/11/2.
 */

public class PopupWindowTest extends Activity{
    private PopupWindow popupWindow;
    private View rootView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow_test);
        Button mButton = (Button) findViewById(R.id.bt_popupwindow);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopupWindowTest.this, "popup window出现",
                        Toast.LENGTH_SHORT).show();
                showPopWindow();
           }
        });

    }

    public void showPopWindow() {
        rootView = LayoutInflater.from(this).inflate(R.layout.activity_popupwindow_test, null);
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(LayoutInflater.from(this).inflate(
                R.layout.layout_popupwindow, null));
        popupWindow.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        //设置位置
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
        popupWindow.setOutsideTouchable(true);



    }
}
