package com.koffuxu.myapplication.bitmap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CanvasPaint extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_bitmap);
		View mView = new CanvasPaintMyView(this);
		setContentView(mView);
	}

}
