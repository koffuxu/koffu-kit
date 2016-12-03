package com.koffuxu.myapplication.prj.floadwindow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.koffuxu.myapplication.R;

public class FloatWindowMainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_float_window_main);
		Button button = (Button) findViewById(R.id.btStartFloat);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), FloatWindowService.class);
				startService(intent);
				finish();
			}
		});
	}




}
