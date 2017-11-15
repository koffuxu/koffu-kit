package com.koffuxu.myapplication.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.koffuxu.myapplication.R;
import java.util.List;


/**
 * Created by XGF on 2017/11/14.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{
    private Context mContext;
    private List<Integer> mList;
    private RecyclerViewTest.OnMyItemClickListener mlistener;

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.iv_item);
        }
    }

    public MyRecyclerAdapter(Context mContext, List<Integer> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    //设置回调接口函数
    public void setmItemClickListener(RecyclerViewTest.OnMyItemClickListener mItemClickListener) {
        this.mlistener= mItemClickListener;
    }

    //1，开始创建的时候，获得ViewHolder里面的单个控件
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        MyViewHolder mHolder = new MyViewHolder(v);
        return mHolder;
    }

    //2，展示的时候分别，单独处理各个控件内容，回调函数放到Activity去处理
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.imageView.setImageResource(mList.get(position));
        if(mlistener != null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mlistener.onMyItemClick(holder.imageView, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
