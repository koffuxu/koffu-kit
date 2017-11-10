package com.koffuxu.myapplication.mvpsample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.koffuxu.myapplication.R;
import com.koffuxu.myapplication.view.UserInforPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import static com.koffuxu.myapplication.MainActivity.TAG;

/**
 * Created by XGF on 2017/11/9.
 */

public class MVPSampleMainActivity extends Activity implements IBaseView, View.OnClickListener{

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private Button bt1;
    private Button bt2;
    private ProgressBar progressBar;

    private UserInforPresenter userInforPresenter;
    private List<EditText> list;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_sample_main);
        init();
        initView();
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

    @Override
    public void clearData() {
        Log.i(TAG, "clearData: ");
        userInforPresenter.initdata(list);
    }

    /**
     * construct presenter class
     */
    @Override
    public void init() {
        userInforPresenter = new UserInforPresenter();
        list = new ArrayList<>();
        context = getApplicationContext();
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()) {
           case R.id.bt1:
               submitData();
               break;
           case R.id.bt2:
               clearData();
               break;
       }
    }
}
