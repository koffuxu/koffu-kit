package com.koffuxu.myapplication.thirdlib.retrofit2_rxjava2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.koffuxu.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.koffuxu.myapplication.MainActivity.TAG;

/**
 * Created by XGF on 2017/11/22.
 */

public class SearchBookActivity extends Activity {

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
        Log.i(TAG, "request: ");
        // 使用原始的方法
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/


        //RetrofitService service = retrofit.create(RetrofitService.class);
        // Call<Book> call = service.getSearchBook("金瓶梅",null,0,1);
    /*    call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                tvRetrofitTest.setText(response.body()+"");
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.i(TAG, "onFailure: retrofit2");
            }
        });*/


        //使用rxjava
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<Book> observable = service.getSearchBook("金瓶梅",null,0,1);
        observable.doOnNext(new Action1<Book>() {
            @Override
            public void call(Book book) {
                Log.i(TAG, "call: ");
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");

                    }

                    @Override
                    public void onNext(Book book) {
                        Log.i(TAG, "onNext: " + book.getBooks());
                        tvRetrofitTest.setText("Book Name" + book.getBooks().get(0).getSummary());

                    }
                });
}
}
