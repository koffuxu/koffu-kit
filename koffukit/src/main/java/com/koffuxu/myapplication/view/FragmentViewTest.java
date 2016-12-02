package com.koffuxu.myapplication.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.koffuxu.myapplication.MainActivity;
import com.koffuxu.myapplication.R;

/**
 * Created by Administrator on 2016/12/2.
 */
public class FragmentViewTest extends Activity implements View.OnClickListener {
    FragmentManager fm= null;
    TextView tv_topbar = null;
    TextView tv_channel = null;
    TextView tv_message =null;
    TextView tv_better=null;
    TextView tv_my=null;
    FrameLayout fl_content=null;

    private MyFragment myF1, myF2, myF3, myF4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MainActivity.TAG, "Fragment View Test");
        setContentView(R.layout.activity_view_test_main);
        fm = getFragmentManager();
        bindView();
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d(MainActivity.TAG, "Fragment View Test");
        setContentView(R.layout.activity_view_test_main);
        fm = getFragmentManager();
        bindView();

        //simulate first click


    }
*/
    public void bindView(){

        tv_topbar = (TextView) findViewById(R.id.fv_top_bar_txt);

        tv_channel = (TextView) findViewById(R.id.tv_channel);
        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_better = (TextView) findViewById(R.id.tv_better);
        tv_my = (TextView) findViewById(R.id.tv_my);

        fl_content = (FrameLayout) findViewById(R.id.fl_content);

        tv_channel.setOnClickListener(this);
        tv_message.setOnClickListener(this);
        tv_better.setOnClickListener(this);
        tv_my.setOnClickListener(this);
    }

    //init click event state
    private  void setSelected(){
        tv_channel.setSelected(false);
        tv_message.setSelected(false);
        tv_better.setSelected(false);
        tv_my.setSelected(false);
    }

    //hide all Fragment
    private void hideAllFragment(FragmentTransaction ft) {
        if(myF1 != null) ft.hide(myF1);
        if(myF2 != null) ft.hide(myF2);
        if(myF3 != null) ft.hide(myF3);
        if(myF4 != null) ft.hide(myF4);

    }


    @Override
    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (v.getId()){
            case R.id.tv_channel:
                setSelected();
                tv_channel.setSelected(true);
                if(myF1 == null){
                    myF1 = new MyFragment("My First Fragment");
                    fragmentTransaction.add(R.id.fl_content,myF1);
                } else {
                    Log.d(MainActivity.TAG, "1st Fragment Create");
                    fragmentTransaction.show(myF1);
                }
                break;
            case R.id.tv_message:
                setSelected();
                tv_message.setSelected(true);
                if(myF2 == null){
                    myF2 = new MyFragment("My Second Fragment");
                    fragmentTransaction.add(R.id.fl_content,myF2);
                } else {
                    fragmentTransaction.show(myF2);
                }
                break;
            case R.id.tv_better:
                setSelected();
                tv_better.setSelected(true);
                if(myF3 == null){
                    myF3 = new MyFragment("My Third Fragment");
                    fragmentTransaction.add(R.id.fl_content,myF3);
                } else {
                    fragmentTransaction.show(myF3);
                }
                break;
            case R.id.tv_my:
                setSelected();
                tv_my.setSelected(true);
                if(myF4 == null){
                    myF4 = new MyFragment("My Fourth Fragment");
                    fragmentTransaction.add(R.id.fl_content,myF4);
                } else {
                    fragmentTransaction.show(myF4);
                }
                break;
        }
        fragmentTransaction.commit();
    }
}
