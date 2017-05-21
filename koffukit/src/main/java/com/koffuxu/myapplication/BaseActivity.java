package com.koffuxu.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by koffuxu on 2017/5/21.
 */
public class BaseActivity extends Activity {
    private static final String TAG = "BaseActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        final Button BtSend = (Button) findViewById(R.id.btSend);
        final EditText etSend = (EditText) findViewById(R.id.etSend);
        final EditText etReceive = (EditText) findViewById(R.id.etReceive);
        BtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cLen=0, bLen=0;
                int resultFlag = 0;
                CharSequence t = etSend.getText();
                cLen = t.length();
                if( cLen <= 0 ) {
                    Log.i(TAG, "Please input HEX string!");
                    return ;
                }else if(cLen % 2 != 0){
                    Log.i(TAG, "The counts of String is not EVEN!");
                    return ;
                }else{
                    bLen = cLen/2;
                }
                char[] cText = new char[cLen];
                byte[] bText = new byte[bLen];
                Log.i(TAG, "get cLen:"+ cLen + " bLen:" + bLen);
                for(int i = 0;  i < cLen; i++){
                    int value = 0;
                    cText[i] = t.charAt(i);
                    cText[i+1] = t.charAt(i+1);
                    Log.i(TAG, "cText[i]="+cText[i]+",cText[i+1]="+cText[i+1]);
                    int b1 = Character.digit(cText[i], 16);
                    int b2 = Character.digit(cText[i+1], 16);
                    if(b1<0 || b2 <0){
                        Log.i(TAG,"please input the HEX number from 1 to F");
                        resultFlag = -1;
                        break;
                    }
                    value = b1*16 + b2;
                    Log.i(TAG,"get cText, b1="+b1+"; b2="+b2+"; value="+value);
                    bText[i/2] = (byte)value;
                    Log.i(TAG,"i="+i+"; bText["+i/2+"] = "+bText[i/2]);
                    ++i;
                }
                if(resultFlag < 0){
                    Log.i(TAG,"Oops: String convert get error, cannot send!");
                    return;
                }
                int temp = bText.length;
                System.out.println("temp len "+ temp);
                for (int j =0; j<temp; j++) {
                    System.out.println("Receive:" + (int)bText[j]);
                }
                //etReceive.setText(bText);

            }
        });
    }
}
