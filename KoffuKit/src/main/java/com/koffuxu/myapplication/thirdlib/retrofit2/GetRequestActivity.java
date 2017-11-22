package com.koffuxu.myapplication.thirdlib.retrofit2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.koffuxu.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.koffuxu.myapplication.MainActivity.TAG;

/**
 * Created by XGF on 2017/11/22.
 */

public class GetRequestActivity extends Activity {

    @BindView(R.id.tv_retrofit_test)
    TextView tvRetrofitTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_retrofit_test);
        ButterKnife.bind(this);
        tvRetrofitTest = (TextView) findViewById(R.id.tv_retrofit_test);
        request();
    }

    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        Call<Translation> call = request.getCall();

        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
                tvRetrofitTest.setText(response.body().getResult());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.i(TAG, "onFailure: retrofit2");
            }
        });
    }
}
