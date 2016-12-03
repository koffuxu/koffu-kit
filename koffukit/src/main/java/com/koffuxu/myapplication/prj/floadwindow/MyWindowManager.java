package com.koffuxu.myapplication.prj.floadwindow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActionBar.LayoutParams;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.koffuxu.myapplication.R;

public class MyWindowManager {

	private static FloadWindowSmallView smallView;
	private static FloadWindowBigView biglView;
	private static WindowManager.LayoutParams smallParams;
	private static WindowManager.LayoutParams bigParams;
	
	private static WindowManager gWindowManager = null;
	private static ActivityManager gActivityManager = null;
	//judge window is show or not
	public static boolean isWindowShowing() {
		return smallView!=null || biglView!=null;
	}

	//small window manage
	public static void createSmallWindow(Context context) {
		Log.d("koffuxu", "createSmallWindow");
		WindowManager windowManager = getWindowManager(context);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics );
		int screenWidth = displayMetrics.widthPixels;
		int screenHeigh = displayMetrics.heightPixels;
		if(smallView == null){
			smallView = new FloadWindowSmallView(context);
			if(smallParams == null){
				Log.d("koffuxu", "set SmallView Parameter");
				smallParams = new WindowManager.LayoutParams();
				smallParams.type = WindowManager.LayoutParams.TYPE_TOAST;//TYPE_PHONE;
				smallParams.format = PixelFormat.RGBA_8888;
				smallParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | 
						WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
				
				smallParams.height = FloadWindowSmallView.viewHeight;
				smallParams.width = FloadWindowSmallView.viewWidth;
				smallParams.gravity = Gravity.LEFT | Gravity.TOP;
				smallParams.x = screenWidth;
				smallParams.y = screenHeigh/2;
			}
			smallView.setParameter(smallParams);
			windowManager.addView(smallView, smallParams);
		}
	}

	public static void removeSmallWindow(Context context) {
		
		if(smallView != null){
			Log.d("koffuxu", "removeSmallWindow");
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(smallView);
			smallView = null;
		}
		
		
	}


	//big window manage
	public static void createBigWindow(Context context) {
		Log.d("koffuxu", "createBigWindow");
		WindowManager windowManager = getWindowManager(context);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics );
		int screenWidth = displayMetrics.widthPixels;
		int screenHeigh = displayMetrics.heightPixels;
		if(biglView == null){
			biglView = new FloadWindowBigView(context);
			if(bigParams == null){
				Log.d("koffuxu", "set BigView Parameter");
				bigParams = new WindowManager.LayoutParams();
				bigParams.type = WindowManager.LayoutParams.TYPE_TOAST;//TYPE_PHONE;
				bigParams.format = PixelFormat.RGBA_8888;
				bigParams.flags = /*WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | */
						WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
				
				bigParams.height = FloadWindowBigView.viewHeight;
				bigParams.width = FloadWindowBigView.viewWidth;
				bigParams.gravity = Gravity.LEFT | Gravity.TOP;
				
				bigParams.x = screenWidth/2 - FloadWindowBigView.viewWidth/2;
				bigParams.y = screenHeigh/2 - FloadWindowBigView.viewHeight/2;
			}
			
			windowManager.addView(biglView, bigParams);
		}
	}

	public static void removeBigWindow(Context context) {

		if(biglView != null){
			Log.d("koffuxu", "removeBigWindow");
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(biglView);
			biglView = null;
		}
		
	}
	
	public static String getUsedPercentValue(Context context) {
	      String dir = "/proc/meminfo";  
	        try {  
	            FileReader fr = new FileReader(dir);  
	            BufferedReader br = new BufferedReader(fr, 2048);  
	            String memoryLine = br.readLine();  
	            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));  
	            br.close();  
	            long totalMemorySize = Integer.parseInt(subMemoryLine.replaceAll("\\D+", ""));  
	            long availableSize = getAvailableMemory(context) / 1024;  
	            int percent = (int) ((totalMemorySize - availableSize) / (float) totalMemorySize * 100);  
	            return percent + "%";  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return "Float Window";
	}
	
	private static long getAvailableMemory(Context context) {  
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();  
        getActivityManager(context).getMemoryInfo(mi);  
        return mi.availMem;  
    }

	//Memory Data Update
	public static void updateUsedPercent(Context context) {
		if(smallView!=null){
			TextView textView = (TextView) smallView.findViewById(R.id.tvPrecent);
			textView.setText(getUsedPercentValue(context));
		}
	}
	
	
	private static WindowManager getWindowManager(Context context) {
		if(gWindowManager == null){
			gWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		}
		return gWindowManager;
	}
	
	private static ActivityManager getActivityManager(Context context) {
		if(gActivityManager == null){
			gActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		}
		return gActivityManager;
	}
	
	

}
