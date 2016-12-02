package com.koffuxu.myapplication.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koffuxu.myapplication.MainActivity;
import com.koffuxu.myapplication.R;

/**
 * Created by Administrator on 2016/12/2.
 */
public class MyFragment extends Fragment {

    private String content;

    public MyFragment(String content) {
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(MainActivity.TAG, "MyFragment onCreate content:" + content);
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        TextView tv = (TextView) view.findViewById(R.id.fv_tv_content);
        tv.setText(content);
        return view;
    }
}
