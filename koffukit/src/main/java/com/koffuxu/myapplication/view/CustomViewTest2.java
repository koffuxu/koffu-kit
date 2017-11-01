package com.koffuxu.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koffuxu.myapplication.R;

import java.util.zip.Inflater;

/**
 * Created by koffuxu on 2017/11/1.
 * refer to:http://blog.csdn.net/guolin_blog/article/details/17357967
 *  1,自绘控件
 * >2,组合控件
 *  3,继承控件
 */

public class CustomViewTest2 extends FrameLayout {

    private Button mButton;
    private TextView mTextView;
    public CustomViewTest2(@NonNull Context context) {
        super(context);
    }

    public CustomViewTest2(@NonNull final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_view_2, this);
        mButton = (Button) findViewById(R.id.button_left);
        mTextView = (TextView) findViewById(R.id.title_text);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "click left button", Toast.LENGTH_LONG ).show();
            }
        });
    }

    public void setTitleText(String title) {
        mTextView.setText(title);
    }
    public void setButtonText(String buttonText){
        mButton.setText(buttonText);
    }
    public void setButtonListener(OnClickListener listener){
        mButton.setOnClickListener(listener);
    }
}
