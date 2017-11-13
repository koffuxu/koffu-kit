package com.koffuxu.myapplication.mvpsample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.koffuxu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koffuxu on 2017/11/11.
 */

public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    private static final String TAG = "LoginFragment";
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private Button bt1;
    private Button bt2;
    private ProgressBar progressBar;

    private LoginContract.Presenter contractPresenter;
    private List<EditText> list = new ArrayList<>();
    private List<String> etList = new ArrayList<String>();

    @Override
    public void onResume() {
        super.onResume();
        init();
        initView();
    }

    public LoginFragment() {
        Log.i(TAG, "LoginFragment: construct!");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_mvp_login, container, false);
        et1 = (EditText) root.findViewById(R.id.et1);
        et2 = (EditText) root.findViewById(R.id.et2);
        et3 = (EditText) root.findViewById(R.id.et3);
        et4 = (EditText) root.findViewById(R.id.et4);
        bt1 = (Button) root.findViewById(R.id.bt1);
        bt2 = (Button) root.findViewById(R.id.bt2);
        progressBar = (ProgressBar) root.findViewById(R.id.submit_probar);
        return root;
    }

    @Override
    public void init() {
        Log.i(TAG, "init: ");
        list.add(et1);
        list.add(et2);
        list.add(et3);
        list.add(et4);
    }

    //porvide for MainActivity
    public void setPresenter(LoginContract.Presenter mPresenter) {
        contractPresenter = mPresenter;

    }


    @Override
    public void initView() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void submitData() {
        Log.i(TAG, "submitData2: ");
        etList.add(et1.getText().toString());
        etList.add(et2.getText().toString());
        etList.add(et3.getText().toString());
        etList.add(et4.getText().toString());
        contractPresenter.submitData2(etList);
    }

    @Override
    public void clearData() {

        Log.i(TAG, "clearData: ");
        //userInforPresenter.initdata(list);
        contractPresenter.initdata(list);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void alterDailogin(int status) {
        Log.i(TAG, "alterDailogin: none");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                //submitData();
                submitData();
                break;
            case R.id.bt2:
                clearData();
                break;

        }
    }
}
