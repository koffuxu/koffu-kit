package com.koffuxu.myapplication.network;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.koffuxu.myapplication.R;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by koffuxu on 2017/7/5.
 */

public class SocketTestActivity extends Activity{
    public static final String TAG = "SocketTestActivity";
    private Button btStart;
    private Button btStop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_socket_test);
        Log.d(TAG, "Service Socket Listener Start");
                new Thread () {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            ServerSocket ss = new ServerSocket();
                            ss.bind(new InetSocketAddress("127.0.0.1",5554));
                            Socket s = ss.accept();
                            Log.d(TAG, "Server Socket Connect succeed");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();

        btStart = (Button) findViewById(R.id.btStartListener);
        btStop = (Button) findViewById(R.id.btStopListener);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Socket Listener Start");
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                            try {
                                //创建socket，监听服务器地址为127.0.0.1:5554
                                Socket clientSct = new Socket("127.0.0.1",5554);
                                //Socket clientSct = new Socket("192.168.123.93",5555);
                                //
                                OutputStream os = clientSct.getOutputStream();
                                os.write("some message from client socket".getBytes());
                                os.flush();
                                clientSct.shutdownOutput();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                }.start();
            }
        });
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Service Socket Listener Start");
                new Thread () {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            ServerSocket ss = new ServerSocket(5554,0,Inet4Address.getLoopbackAddress());
                            Socket s = ss.accept();
                            Log.d(TAG, "Server Socket Connect succeed");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();

            }
        });
    }

}
