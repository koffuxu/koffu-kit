package com.koffuxu.myapplication.thirdlib.retrofit2;

import android.util.Log;

/**
 * Created by XGF on 2017/11/22.
 */

class Translation {
    private final static String TAG = "Translation";
    private int status;
    private Content content;

    public static class Content {
        private String from;
        private String to;
        private String vendor;

        public String getOut() {
            return out;
        }

        public void setOut(String out) {
            this.out = out;
        }

        private String out;
        private int errNo;

        @Override
        public String toString() {
            return "Content{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", vendor='" + vendor + '\'' +
                    ", out='" + out + '\'' +
                    ", errNo=" + errNo +
                    '}';
        }
    }

    public void show(){
        Log.i(TAG, "show: status="+status+", content="+content.toString());
    }
    public String getResult(){
        return content.getOut();
    }


}
