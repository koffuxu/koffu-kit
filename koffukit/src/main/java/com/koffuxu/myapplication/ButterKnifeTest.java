package com.koffuxu.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by koffuxu on 2017/10/24.
 * refer to:http://www.cnblogs.com/cnwutianhao/p/6610529.html
 */

class ButterKnifeTest extends Activity {
    @BindView(R.id.btStartListener)
    Button btStartListener;
    @BindView(R.id.btStopListener)
    Button btStopListener;
    @BindView(R.id.editText)
    TextView editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btStartListener, R.id.btStopListener})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btStartListener:
                Toast.makeText(this, "from Butter Knife start Click Event", Toast.LENGTH_LONG).show();
                break;
            case R.id.btStopListener:
                Toast.makeText(this, "from Butter Knife stop Click Event", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
