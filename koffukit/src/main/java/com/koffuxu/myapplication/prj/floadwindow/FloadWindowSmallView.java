package com.koffuxu.myapplication.prj.floadwindow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koffuxu.myapplication.R;

public class FloadWindowSmallView extends LinearLayout {
	
	public static int viewWidth;
	public static int viewHeight;
	
	//
	private static WindowManager.LayoutParams mLayoutParams;
	private static float statusBarHeight;

	//recode the original coordinate axis, for judge is MOVE or ONE-CLICK
	private static float viewOrgX;
	private static float viewOrgY;
	
	//SmallView coordinate axis
	private static float viewX;
	private static float viewY;
	
	//after finger move ,rawX rawY
	private static float viewCurX;
	private static float viewCurY;
	

	private WindowManager windowManager = null;
	public FloadWindowSmallView(Context context) {
		super(context);
		windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		LayoutInflater.from(context).inflate(R.layout.float_window_small, this);
		TextView textView = (TextView) findViewById(R.id.tvPrecent);
		textView.setText(MyWindowManager.getUsedPercentValue(context));
		View view = findViewById(R.id.llFloatWindowSmall);
		viewHeight = view.getLayoutParams().height;
		viewWidth = view.getLayoutParams().width;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			viewOrgX = event.getRawX();
			viewOrgY = event.getRawY();
			
			viewCurX = event.getRawX();
			viewCurY = event.getRawY();
		//
			viewX = event.getX();
			viewY = event.getY();
			
			Log.d("koffuxu", "DOWN:viewOrgX="+viewOrgX+"; viewX="+viewX);
			
			break;
			
		case MotionEvent.ACTION_MOVE:
			viewCurX = event.getRawX();
			viewCurY = event.getRawY();
			Log.d("koffuxu", "MOVE:viewOrgX="+viewCurX+"; viewX="+viewX);
			updateView();
			break;
		case MotionEvent.ACTION_UP:
			Log.d("koffuxu", "UP:viewCurX="+viewCurX+"; viewCurY="+viewCurY);
			if((viewOrgX == viewCurX) &&(viewOrgY == viewCurY))
				openBigWindow();
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	//update view
	private void updateView(){
		mLayoutParams.x = (int) (viewCurX - viewX);
		mLayoutParams.y = (int) (viewCurY - viewY);
		windowManager.updateViewLayout(this, mLayoutParams);
	}

	//recode create small parameter
	public void setParameter(WindowManager.LayoutParams params){
		mLayoutParams = params;
	}
	//get status bar height
	private int getStatusBarHeight(){
		//TODO
		return 0;
	}
	
	private void openBigWindow(){
		MyWindowManager.createBigWindow(getContext());
		MyWindowManager.removeSmallWindow(getContext());
	}

}
