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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.koffuxu.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by koffuxu on 2017/10/31.
 * http://www.androidchina.net/6160.html
 */

public class RecyclerViewTest extends Activity {
    private static final String TAG = "RecyclerViewTest";
    private RecyclerView mRecyclerView;
    private List<Integer> mDatas;
    private MyRecyclerAdatper mAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recycler_view);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_test);
        //设置布局管理器
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        //设置适配器,把自定义的数据mDatas传入
        mAdapter = new MyRecyclerAdatper(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //设置每个item监听事件
        mAdapter.setmItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onMyItemClick(View view, int postion) {
                Toast.makeText(RecyclerViewTest.this, "点击了第 "+postion+" 个ITEM",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    void initData(){
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.p1,
                R.drawable.p2, R.drawable.p3, R.drawable.p4,
                R.drawable.p5, R.drawable.p6
                ));

    }

    //item的回调接口
    public interface OnMyItemClickListener{
        void onMyItemClick(View view, int postion);
    }
    //继承自泛型为自定义MyHoler的Adapter
    public class MyRecyclerAdatper extends RecyclerView.Adapter<MyRecyclerAdatper.MyHolder> {
        private Context mContext;
        private List<Integer> mDatas;
        private OnMyItemClickListener mItemClickListener;

        //构造函数
        public MyRecyclerAdatper(Context context, List<Integer> datas){
            super();
            this.mContext = context;
            this.mDatas = datas;
        }

        //设置回调接口函数
        public void setmItemClickListener(OnMyItemClickListener mItemClickListener) {
            this.mItemClickListener = mItemClickListener;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder");
            View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            MyHolder myHolder = new MyHolder(view);
            return myHolder;
        }




        @Override
        public void onBindViewHolder(final MyHolder holder, final int position) {
            holder.imageView.setImageResource(mDatas.get(position));
            //holder.imageView.setImageResource(R.drawable.pet);
            //回调处理
            if(mItemClickListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onMyItemClick(holder.itemView, position);
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }


        //内部类MyHolder，来获得item中的控件
        class MyHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            public MyHolder(View v){
                super(v);
                imageView = (ImageView)v.findViewById(R.id.iv_item);
            }
        }
    }
}
