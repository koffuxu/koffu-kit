package com.koffuxu.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/11/15.
 */
public class LocalService extends Service {
    static final String TAG="koffuxu";

    public class LocalBinder extends Binder {
        LocalService getService(){
            return LocalService.this;
        }
    }
    private IBinder mBinder = new LocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"Local Service onBind");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Local Service onDestroy");
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Local Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Local Service onStartCommand");
        Log.d(TAG, "Local Service flags:"+flags+", startId:"+startId);
        return START_NOT_STICKY;
    }

    //Business Function
    public int add(int a, int b){
        return (a + b);
    }
    public int sub(int a, int b){
        return (a - b);
    }
}
