package com.koffuxu.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.koffuxu.myapplication.R;

import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * Created by koffuxu on 2017/11/1.
 * refer to:http://blog.csdn.net/guolin_blog/article/details/17357967
 *  1,自绘控件
 *  2,组合控件
 * >3,继承控件
 */

public class CustomViewTest3 extends AppCompatImageView implements View.OnClickListener{
    private Movie mMovie;
    private boolean isAutoPlay;
    private boolean isPalying;
    //GIF图片宽高
    private int mImageWidth;
    private int mImageHeight;
    private long mMovieStart;
    private Bitmap mStartButton;



    public CustomViewTest3(Context context) {
        super(context);
    }

    public CustomViewTest3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        Log.d("CustomViewTest3", "constractor 2");
    }

    public CustomViewTest3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("CustomViewTest3", "constractor 3");
        //通过TaypedArray获得自定义的属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PowerImageView);

        //获得图片ID
        int resourceId = getResourceId(a, context, attrs);
        Log.d("CustomViewTest3", "constractor 3, resouceId:"+resourceId);
        if(resourceId != 0) {
            InputStream is = getResources().openRawResource(resourceId);
            mMovie = Movie.decodeStream(is);
            Log.d("CustomViewTest3", "constractor 3, mMovie:"+mMovie.toString());
            if(mMovie !=null){
                //获得自定义属性的值
                isAutoPlay = a.getBoolean(R.styleable.PowerImageView_auto_play, false);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                mImageHeight = bitmap.getHeight();
                mImageWidth = bitmap.getWidth();
                bitmap.recycle();
                if(!isAutoPlay){
                    mStartButton = BitmapFactory.decodeResource(getResources(),
                            R.drawable.start_play);
                    setOnClickListener(this);
                }
            }

        }

    }
//通过反射机制获得图片资源所对应的id
    private int getResourceId(TypedArray a, Context context, AttributeSet attrs) {
        try {
            Field field = TypedArray.class.getDeclaredField("mValue");
            field.setAccessible(true);
            TypedValue typedValue = (TypedValue) field.get(a);
            return typedValue.resourceId;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == getId()) {
            isPalying = true;
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("CustomViewTest3", "onMeasure:W-"+mImageWidth+"; H-"+mImageHeight);
        if(mMovie != null){
            //设置图片长宽
            setMeasuredDimension(mImageWidth,mImageHeight);
        }
    }

    //TODO:cannot play gif
    @Override
    protected void onDraw(Canvas canvas) {
        //Log.d("CustomViewTest3", "onDraw, isAutoPlay"+isAutoPlay);
        if(mMovie == null){
            super.onDraw(canvas);
        }else {
            if(isAutoPlay){
                Log.d("CustomView3", "is auto play");
                playMovie(canvas);
                invalidate();
            } else {
                if(isPalying) {
                    Log.d("CustomView3", "is playing");
                    if(playMovie(canvas)){
                        isPalying = false;
                    }
                    invalidate();
                }else {
                    Log.d("CustomView3", "start play");
                    mMovie.setTime(0);
                    mMovie.draw(canvas,0,0);
                    int offsetW = (mImageWidth - mStartButton.getWidth())/2;
                    int offsetH = (mImageHeight - mStartButton.getHeight())/2;
                    canvas.drawBitmap(mStartButton, offsetW, offsetH, null);
                }
            }
        }
    }

    private boolean playMovie(Canvas canvas) {
        //Log.d("CustomView3", "playMove:"+canvas.toString());
        long now = SystemClock.uptimeMillis();
        if (mMovieStart == 0){
            mMovieStart = now;
        }
        int duration = mMovie.duration();
        if(duration == 0) {
            duration = 1000;
        }
        int relTime = (int)((now - mMovieStart)%duration);
        mMovie.setTime(relTime);
        mMovie.draw(canvas,0,0);
        if((now - mMovieStart)>=duration) {
            mMovieStart = 0;
            return true;
        }
        return false;
    }

}
