package com.koffuxu.myapplication.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.koffuxu.myapplication.IRemoteService;
import com.koffuxu.myapplication.R;

public class Client extends AppCompatActivity {
    static final String TAG="koffuxu";
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Intent intent;
    private Intent intent2;
    public LocalService mLocalService = null;
    public IRemoteService mRemoteService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Client onCreate");
        setContentView(R.layout.activity_local_client);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);
        intent = new Intent();
        intent.setClass(this, LocalService.class);



        //1, use startService
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "start service");
                startService(intent);
                Toast.makeText(Client.this,"Start Local Service ", Toast.LENGTH_SHORT).show();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "stop service");
                stopService(intent);
                Toast.makeText(Client.this,"Stop Local Service ", Toast.LENGTH_SHORT).show();
            }
        });

        //2, use bindService for RemoteService
        intent2 = new Intent();
        intent2.setClass(this, RemoteService.class);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "bind service");
                bindService(intent2,mConnect,BIND_AUTO_CREATE);

            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            int result = 0;
            @Override
            public void onClick(View v) {
                //-------Business Invoking
                if (mLocalService != null){
                    result = mLocalService.add(3, 5);//used Local Service
                    Log.d(TAG, "result =" + result);
                    Toast.makeText(Client.this,"Local Service get the resule=" + result, Toast.LENGTH_SHORT).show();
                }else {
                    Log.d(TAG, "mLocalService is null!");
                }
                //------AIDL Remote Service
                if (mRemoteService!= null){
                    try {
                        result = mRemoteService.add(3, 5); //used Remote Service
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "result =" + result);
                    Toast.makeText(Client.this,"Remote Service get the resule=" + result, Toast.LENGTH_SHORT).show();
                }else {
                    Log.d(TAG, "mRemoteService is null!");
                }
            }
        });

    }

    ServiceConnection mConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            mLocalService = ((LocalService.LocalBinder) service).getService();
            Log.e(TAG, "ComponentName:" + name);
            try {
                Log.d(TAG, "getInterfaceDescriptor:" + service.getInterfaceDescriptor());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mRemoteService = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //mLocalService = null;
            mRemoteService = null;

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConnect != null) {
            Log.d(TAG, "mConnect=" + mConnect.toString());
         //TODO unbindService(mConnect);
        }
    }
}
