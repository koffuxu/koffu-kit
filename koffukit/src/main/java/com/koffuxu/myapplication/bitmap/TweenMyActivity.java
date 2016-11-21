package com.koffuxu.myapplication.bitmap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.koffuxu.myapplication.R;

public class TweenMyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tween);
		ImageView imageView = (ImageView) findViewById(R.id.IvTween);
		Animation animation = (Animation) AnimationUtils.loadAnimation(this, R.anim.tween);
		imageView.setAnimation(animation);
	}


}
