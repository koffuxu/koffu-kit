package com.koffuxu.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by koffuxu on 2017/11/1.
 * refer to:http://blog.csdn.net/guolin_blog/article/details/17357967
 * >1,自绘控件
 *  2,组合控件
 *  3,继承控件
 */

public class CustomViewTest1 extends View implements View.OnClickListener {
    private Paint mPaint;
    private Rect mRect;
    private int mCount;

    public CustomViewTest1(Context context) {
        super(context);
    }

    public CustomViewTest1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text,0, text.length(), mRect);
        float textWidth = mRect.width();
        float textHeight = mRect.height();
        canvas.drawText(text, getWidth()/2 - textWidth/2, getHeight()/2 + textHeight/2, mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
