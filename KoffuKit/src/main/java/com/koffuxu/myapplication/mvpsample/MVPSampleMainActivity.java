package com.koffuxu.myapplication.mvpsample;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.koffuxu.myapplication.R;
import com.koffuxu.myapplication.daogen.DaoMaster;
import com.koffuxu.myapplication.daogen.DaoSession;
import com.koffuxu.myapplication.daogen.UserInfo;
import com.koffuxu.myapplication.daogen.UserInfoDao;

import java.util.List;

/**
 * Created by XGF on 2017/11/9.
 */

public class MVPSampleMainActivity extends Activity /*implements LoginContract.View, View.OnClickListener*/{

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private Button bt1;
    private Button bt2;
    private ProgressBar progressBar;

    private UserInforPresenter userInforPresenter;
    private UserInforPresenter userInforPresenter2;
    private List<EditText> list;
    private Context context;
    private LoginFragment loginFragment;

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserInfoDao userInfoDao;


    private void openDb(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MVPSampleMainActivity.this,"userinfo.db",null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        userInfoDao = daoSession.getUserInfoDao();

    }

    private void addUserInfo(){
        UserInfo userInfo= new UserInfo();
        //userInfoLegacy1.setAge(18);
        userInfo.setGender("M");
        userInfo.setHobby("Play");
        userInfo.setName("Jhon");
        userInfoDao.insert(userInfo);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mvp_sample_main);
        setContentView(R.layout.activity_mvp_sample_main_fragment);
        init2();
        initView2();
        //GreenDao Test
        openDb();
        addUserInfo();

       //init();
        //initView();
    }
    /*
    * for fragment inside mode
     */
    public void init2(){
        loginFragment = new LoginFragment();
        UserInfo_Legacy userInfoLegacy = new UserInfo_Legacy();
        userInforPresenter2 = new UserInforPresenter(userInfoLegacy, loginFragment);
        loginFragment.setPresenter(userInforPresenter2);

    }

    private void initView2() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragTransction = fm.beginTransaction();
        fragTransction.add(R.id.mvp_main_fragment, loginFragment);
        fragTransction.commit();
    }

        /**
     * construct presenter class
     */
    /**
     *
     *
    @Override
    public void init() {
        //userInforPresenter = new UserInforPresenter();
        UserInfo_Legacy userInfo = new UserInfo_Legacy();
        userInforPresenter2 = new UserInforPresenter(userInfo, this);
        list = new ArrayList<>();
        context = getApplicationContext();
    }

    @Override
    public void initView() {
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        list.add(et1);
        list.add(et2);
        list.add(et3);
        list.add(et4);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.submit_probar);
    }

    @Override
    public void submitData() {
        Log.i(TAG, "submitData: ");
       userInforPresenter.submitData(context,list,progressBar);

    }

    public void submitData2() {
        Log.i(TAG, "submitData2: ");
        List<String> etList = new ArrayList<String>();
        etList.add(et1.getText().toString());
        etList.add(et2.getText().toString());
        etList.add(et3.getText().toString());
        etList.add(et4.getText().toString());
        userInforPresenter2.submitData2(etList);

    }

    @Override
    public void clearData() {
        Log.i(TAG, "clearData: ");
        //userInforPresenter.initdata(list);
        userInforPresenter2.initdata(list);
    }



    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        Log.i(TAG, "hideProgressBar: ");
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
       switch (view.getId()) {
           case R.id.bt1:
               //submitData();
               submitData2();
               break;
           case R.id.bt2:
               clearData();
               break;
       }
    }

    @Override
    public void alterDailogin(int status) {
        Toast.makeText(MVPSampleMainActivity.this,"Login status is:"
                + status,Toast.LENGTH_SHORT).show();
    }
    */
}
