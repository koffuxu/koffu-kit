package com.koffuxu.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.koffuxu.myapplication.IRemoteService;

/**
 * Created by Administrator on 2016/11/16.
 */
public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("koffuxu", "RemoteService OnBind");
        return mBinder;
    }
    private final IRemoteService.Stub mBinder = new IRemoteService.Stub(){


        @Override
        public int add(int a, int b) throws RemoteException {
            return (a+b);
        }
    };
}
