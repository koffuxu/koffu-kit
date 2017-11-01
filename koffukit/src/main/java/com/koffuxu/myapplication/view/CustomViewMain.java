package com.koffuxu.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.koffuxu.myapplication.R;

/**
 * Created by koffuxu on 2017/11/1.
 */

public class CustomViewMain extends Activity{
    CustomViewTest2 customViewTest2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_main);
        customViewTest2 = (CustomViewTest2) findViewById(R.id.custom2);
        customViewTest2.setTitleText("123");
        customViewTest2.setButtonListener(new View.OnClickListener() {
            int i;
            @Override
            public void onClick(View v) {
                customViewTest2.setButtonText("BK"+i);
                i++;
                if(i == 10)
                    i=0;
            }
        });
    }
}
