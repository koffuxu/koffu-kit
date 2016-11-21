package com.koffuxu.myapplication.bitmap;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.koffuxu.myapplication.R;


public class BitmapTest extends Activity {
	private ImageView iv;
	private Bitmap bm;
	private InputStream is;
    private Boolean revertFlag= true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bitmap_main);
		iv = (ImageView) findViewById(R.id.imageView1);
		AssetManager am = this.getAssets();
		try {
			is = am.open("page.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bm = BitmapFactory.decodeStream(is);
		iv.setImageBitmap(bm);
		
	}
	public void btOnclick(View view){
        Bitmap scaleBitmap = null;
        if(revertFlag == true){
            scaleBitmap = Bitmap.createScaledBitmap(bm, bm.getWidth()*2, bm.getHeight(), false);
            revertFlag = false;
        }else {
            scaleBitmap = Bitmap.createScaledBitmap(bm, bm.getWidth(), bm.getHeight(), false);
            revertFlag = true;
        }
		if(bm != null && !bm.isRecycled()){
			Log.d("koffuxu", "resycle bitmap!");
		}
		iv.setImageBitmap(scaleBitmap);
	}

}
