package com.koffuxu.myapplication.mvpsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.koffuxu.myapplication.daogen.DaoMaster;
import com.koffuxu.myapplication.daogen.DaoSession;
import com.koffuxu.myapplication.daogen.UserInfo;
import com.koffuxu.myapplication.daogen.UserInfoDao;

import java.util.List;

/**
 * Created by XGF on 2017/11/9.
 */

public class UserInforPresenter implements LoginContract.Presenter{
    private final static String TAG = "UserInfoPresenter";
    private UserInfo UserInfo;
    private IBaseView baseView;

     private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserInfoDao userInfoDao;


    private void openDb(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"userinfo.db",null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        userInfoDao = daoSession.getUserInfoDao();

    }

    private void addUserInfo(){
        UserInfo userInfo= new UserInfo();
        //UserInfo1.setAge(18);
        userInfo.setGender("M");
        userInfo.setHobby("Play");
        userInfo.setName("Jhon");
        userInfoDao.insert(userInfo);

    }

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

    public UserInforPresenter(UserInfo UserInfo, IBaseView view, Context context) {
        this.UserInfo = UserInfo;
        this.baseView = view;
        openDb(context);
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
                UserInfo.setName(list.get(0).getText().toString());
                UserInfo.setAge(Integer.parseInt(list.get(1).getText().toString()));
                UserInfo.setGender(list.get(2).getText().toString());
                UserInfo.setHobby(list.get(3).getText().toString());

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
                UserInfo.setName(list.get(0));
                UserInfo.setAge(Integer.parseInt(list.get(1).toString()));
                UserInfo.setGender(list.get(2));
                UserInfo.setHobby(list.get(3));
                //提交到数据库
                System.out.println("write to db");
                userInfoDao.insert(UserInfo);


                handler2.sendEmptyMessage(1);
            }
        }.start();

    }

    @Override
    public void initdata(List<EditText> list) {
        Log.i(TAG, "before clear data: ");
        Log.i(TAG, "had store data:"+ UserInfo.toString());
        for(EditText editText:list) {
            editText.setText("");
        }

    }
}
