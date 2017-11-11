package com.koffuxu.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koffuxu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koffuxu on 2017/11/2.
 * http://www.jianshu.com/p/adb21180862a
 */

public class ViewPageTest extends Activity{
    private ViewPager viewPager;
    private List<View> mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        initData();
        viewPager.setAdapter(new ViewPageAdapter(mListView));
    }

    private void initData() {
        mListView = new ArrayList<View>();
        LayoutInflater inflater = getLayoutInflater().from(ViewPageTest.this);
        View v1 = inflater.inflate(R.layout.viewpaget_1, null);
        View v2 = inflater.inflate(R.layout.viewpaget_2, null);
        View v3 = inflater.inflate(R.layout.viewpaget_3, null);
        mListView.add(v1);
        mListView.add(v2);
        mListView.add(v3);
    }

    public class ViewPageAdapter extends PagerAdapter{
        private List<View> mViewList;

        public ViewPageAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }
    }
}
