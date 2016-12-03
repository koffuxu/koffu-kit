package com.koffuxu.myapplication.prj.floadwindow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.koffuxu.myapplication.R;

public class FloadWindowBigView extends LinearLayout {
	public static int viewWidth;
	public static int viewHeight;

	public FloadWindowBigView(Context context) {
		super(context);
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
		View view = findViewById(R.id.llFloatWindowBig);
		viewHeight = view.getLayoutParams().height;
		viewWidth = view.getLayoutParams().width;
		
		Button btClose = (Button) findViewById(R.id.btClose);
		btClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyWindowManager.removeBigWindow(getContext());
				MyWindowManager.removeSmallWindow(getContext());
				Intent intent = new Intent(getContext(), FloatWindowService.class);
				getContext().stopService(intent);
			}
		});
		
		Button btBack = (Button) findViewById(R.id.btBack);
		btBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyWindowManager.removeBigWindow(getContext());
				MyWindowManager.createSmallWindow(getContext());
			}
		});
		
	}

}
