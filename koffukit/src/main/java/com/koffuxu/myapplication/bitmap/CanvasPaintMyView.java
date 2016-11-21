package com.koffuxu.myapplication.bitmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class CanvasPaintMyView extends View{

	public CanvasPaintMyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Rect r = new Rect(100, 100, 500, 500);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		canvas.drawRect(r, paint);
		
		paint.setColor(Color.BLUE);
		canvas.drawCircle(200, 200, 50, paint);
	}

}
