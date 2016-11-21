package com.koffuxu.myapplication.bitmap;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class MatrixActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_surfaceview);
		View view = new MatrixMyView(this);
		setContentView(view);
	}


}
