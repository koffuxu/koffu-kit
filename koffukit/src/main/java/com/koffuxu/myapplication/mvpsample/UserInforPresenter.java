package com.koffuxu.myapplication.mvpsample;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.koffuxu.myapplication.mvpsample.IBasePresenter;
import com.koffuxu.myapplication.mvpsample.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XGF on 2017/11/9.
 */

public class UserInforPresenter implements LoginContract.Presenter{
    private final static String TAG = "UserInfoPresenter";
    private UserInfo userInfo;
    private IBaseView baseView;

    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    baseView.showProgressBar();
                    break;
                case 1:
                    baseView.hideProgressBar();
                    break;
            }
        }
    };

    public UserInforPresenter(UserInfo userInfo, IBaseView view) {
        this.userInfo = userInfo;
        this.baseView = view;
    }

    @Override
    public void submitData(Context context, final List<EditText> list, final ProgressBar progressBar) {
        Log.i(TAG, "submitData: ");

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        progressBar.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        };

        progressBar.setVisibility(View.VISIBLE);

        //final Handler mHandler = new Handler(Looper.getMainLooper());
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userInfo.setName(list.get(0).getText().toString());
                userInfo.setAge(list.get(1).getText().toString());
                userInfo.setGender(list.get(2).getText().toString());
                userInfo.setHobby(list.get(3).getText().toString());

                handler.sendEmptyMessage(1);
            }
        }.start();


    }

    /**
     * 把控件的操作全部放到View层，数据与业务逻辑放在Presenter层
     */
    @Override
    public void submitData2(final List<String> list) {
        //simulate sibmit transcation
        new Thread(){

            @Override
            public void run() {
                handler2.sendEmptyMessage(0);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userInfo.setName(list.get(0));
                userInfo.setAge(list.get(1));
                userInfo.setGender(list.get(2));
                userInfo.setHobby(list.get(3));

                handler2.sendEmptyMessage(1);
            }
        }.start();

    }

    @Override
    public void initdata(List<EditText> list) {
        Log.i(TAG, "before clear data: ");
        Log.i(TAG, "had store data:"+userInfo.toString());
        for(EditText editText:list) {
            editText.setText("");
        }

    }
}
