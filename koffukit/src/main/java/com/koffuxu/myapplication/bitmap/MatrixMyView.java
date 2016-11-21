package com.koffuxu.myapplication.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.koffuxu.myapplication.R;

public class MatrixMyView extends View {
	private Bitmap bitmap ;
	private Matrix matrix;
	private boolean zoom = true;
	private float scale = (float) 0.5;
	private long clickOne = 0 ;
	

	public MatrixMyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Resources mResources = context.getResources();
		BitmapDrawable bDrawable = (BitmapDrawable) mResources.getDrawable(R.drawable.page);
		bitmap = bDrawable.getBitmap();
		matrix = new Matrix();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.d("koffuxu", "onDraw");
		matrix.setScale(scale, scale);
		canvas.drawBitmap(bitmap, matrix, null);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d("koffuxu", "current="+System.currentTimeMillis() + "; clickOne="+clickOne);
			if(System.currentTimeMillis() - clickOne > 800){
				Log.d("koffuxu", "double click big than 800ms");
				clickOne = System.currentTimeMillis();
			}else {
				if(zoom == true){
					Log.d("koffuxu", "zoom in");
					scale = scale * 2;
					zoom = false;
				}else if(zoom == false){
					Log.d("koffuxu", "zoom out");
					scale = (float) 0.5;
					zoom = true;
				}
				postInvalidate();
			}
			break;

		default:
			break;
		}
	
		return true;
	}
	
	

}
