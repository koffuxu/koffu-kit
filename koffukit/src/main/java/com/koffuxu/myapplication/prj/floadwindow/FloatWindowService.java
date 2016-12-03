package com.koffuxu.myapplication.prj.floadwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class FloatWindowService extends Service {
	
	private Timer timer = null;
	private Handler handler = new Handler();
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		if(timer !=null){
			Log.d("koffuxu", "Timer canceled!");
			timer.cancel();
			timer = null;
		}
		super.onDestroy();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("koffuxu", "FloadWindowService startCommand");
		if(timer == null){
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					//If is Launcher active and float widow not show, then show it.
					if(isHome() && !MyWindowManager.isWindowShowing()){
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								// start FloatWindow
								MyWindowManager.createSmallWindow(getApplicationContext());
								
							}
						});
					}
					//if the Launcher inactive, and float window is showed, remove it.
					else if (!isHome() && MyWindowManager.isWindowShowing()) {
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								MyWindowManager.removeSmallWindow(getApplicationContext());
								MyWindowManager.removeBigWindow(getApplicationContext());
							}
						});
					}
					//update date
					else if (isHome() && MyWindowManager.isWindowShowing()) {
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								MyWindowManager.updateUsedPercent(getApplicationContext());
							}
						});
					}
				}
			}, 0, 500);
		}
		return super.onStartCommand(intent, flags, startId);
	}
	

	//judge is Home Launcher or not;
	public boolean isHome() {
		ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> rti = activityManager.getRunningTasks(1);
		return getHomes().contains(rti.get(0).topActivity.getPackageName());
	}
	//acquire Home Launcher app package name;
	private List<String> getHomes() {
		List<String> names = new ArrayList<String>();  
        PackageManager packageManager = this.getPackageManager();  
        Intent intent = new Intent(Intent.ACTION_MAIN);  
        intent.addCategory(Intent.CATEGORY_HOME);  
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,  
                PackageManager.MATCH_DEFAULT_ONLY);  
        for (ResolveInfo ri : resolveInfo) {  
            names.add(ri.activityInfo.packageName);  
        }  
        return names;
	}

}
