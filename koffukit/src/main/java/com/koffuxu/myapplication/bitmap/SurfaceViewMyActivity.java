package com.koffuxu.myapplication.bitmap;


import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.koffuxu.myapplication.R;

public class SurfaceViewMyActivity extends Activity {

	private SurfaceView sView;
	private SurfaceHolder sHolder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_surfaceview);
		sView = (SurfaceView) findViewById(R.id.surfaceView1);
		sHolder = sView.getHolder();
		sHolder.addCallback(new Callback() {

			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceCreated(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		sView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if(arg1.getAction() == MotionEvent.ACTION_DOWN) {
					int cx = (int)arg1.getX();
					int cy = (int)arg1.getY();
					Canvas canvas = sHolder.lockCanvas();
					Paint paint = new Paint();
					paint.setColor(Color.RED);
					//clear screen
					canvas.drawColor(Color.BLACK);
					canvas.drawCircle(cx, cy, 50, paint);
					sHolder.unlockCanvasAndPost(canvas);
					
				}
				if(arg1.getAction() == MotionEvent.ACTION_UP){
					//clear sView
					
				}
				return false;
			}
			
		});
	}
	
	


}
