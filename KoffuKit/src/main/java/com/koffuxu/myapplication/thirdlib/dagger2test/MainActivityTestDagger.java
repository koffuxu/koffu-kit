package com.koffuxu.myapplication.thirdlib.dagger2test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.koffuxu.myapplication.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XGF on 2017/11/21.
 */

public class MainActivityTestDagger extends Activity {

    @Inject
    Cloth cloth;

    @Inject
    Shoe shoe;

    @Inject
    ClothesDaggerTest clothesDaggerTest;
    TextView tvDaggerTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_main);
        tvDaggerTest = (TextView) findViewById(R.id.tv_dagger_test);

        MainComponet build = DaggerMainComponet
                .builder()
                .mainModule(new MainModule())
                .build();
        build.inJect(this);
        tvDaggerTest.setText("我现在有" + cloth + "和" + shoe + "和" + clothesDaggerTest);
    }
}
