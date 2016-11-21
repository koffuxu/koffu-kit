package com.koffuxu.myapplication.bitmap;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.koffuxu.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class AnimationTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		ImageView iv = (ImageView) findViewById(R.id.ivAnimal);
		final AnimationDrawable aDrawable = (AnimationDrawable) iv.getDrawable();
		aDrawable.start();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				aDrawable.stop();
			}
		}, 50*1000);
	}


}
