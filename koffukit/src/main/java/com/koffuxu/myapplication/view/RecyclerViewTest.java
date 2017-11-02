package com.koffuxu.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.koffuxu.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by koffuxu on 2017/10/31.
 * http://www.androidchina.net/6160.html
 */

public class RecyclerViewTest extends Activity {
    private static final String TAG = "RecyclerViewTest";
    private RecyclerView mRecyclerView;
    private List<Integer> mDatas;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recycler_view);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_test);
        //设置布局管理器
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        //llm.setOrientation(LinearLayoutManager.VERTICAL);

        //设置适配器
        mRecyclerView.setAdapter(new MyRecyclerAdatper(this,mDatas));

    }

    void initData(){
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.p1,
                R.drawable.p2, R.drawable.p3, R.drawable.p4,
                R.drawable.p5, R.drawable.p6
                ));

    }

    private class MyRecyclerAdatper extends RecyclerView.Adapter<MyRecyclerAdatper.MyHolder> {
        private Context mContext;
        private List<Integer> mDatas;

        public MyRecyclerAdatper(Context context, List<Integer> datas){
            super();
            this.mContext = context;
            this.mDatas = datas;
        }


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder");
            View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            MyHolder myHolder = new MyHolder(view);
            return myHolder;
        }

        class MyHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            public MyHolder(View v){
                super(v);
                imageView = (ImageView)v.findViewById(R.id.iv_item);
            }
        }


        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.imageView.setImageResource(mDatas.get(position));
            //holder.imageView.setImageResource(R.drawable.pet);

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }
}
