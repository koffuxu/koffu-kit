package com.koffuxu.myapplication.bitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.koffuxu.myapplication.R;

/**
 * http://www.cnblogs.com/zealotrouge/p/3380682.html
 * Created by koffuxu on 2017/10/28.
 */

public class BitmapReflectionActivity extends Activity{
    private Bitmap orgBitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        ImageView imageView = (ImageView) findViewById(R.id.IvTween);
        //orgBitmap = imageView.getDrawingCache();
        orgBitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        //获得原始图片大小
        int width = orgBitmap.getWidth();
        int height = orgBitmap.getHeight();
        //Y轴反向
        Matrix matrix = new Matrix();
        matrix.setScale(1,-1);
        //获得图片下半部分
        Bitmap reflectionImage = Bitmap.createBitmap(orgBitmap, 0, height/2, width, height/2, matrix, false);
        //创建原图+间隔+下半部倒影的bitmap对象
        int gapHeight = 8;
        Bitmap bitmapWithRefle = Bitmap.createBitmap(width, (height + gapHeight + height/2), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapWithRefle);
        //设置图片抗锯齿效果
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG));
        //1,绘制原图
        canvas.drawBitmap(orgBitmap, 0, 0, null);
        //2,绘制间距
        Paint gapPaint = new Paint();
        gapPaint.setColor(0x00CCCCCC);
        canvas.drawRect(0, height, width, height+gapHeight, gapPaint);
        //3,绘制倒影
        canvas.drawBitmap(reflectionImage, 0 ,height+gapHeight, null);

        //设置渐变效果
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, height, 0, (height +
        gapHeight + height/2), 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //渲染倒影+渐变
        canvas.drawRect(0, height, width, (height + gapHeight + height/2), paint);

        //绘制
        imageView.setImageBitmap(bitmapWithRefle);

        //释放内存
        orgBitmap.recycle();
        reflectionImage.recycle();
        //bitmapWithRefle.recycle();


    }
}
