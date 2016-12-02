package com.koffuxu.myapplication.fileOps;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koffuxu.myapplication.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/11/17.
 */
public class CopyLogActivity extends Activity {
    static final String TAG = "koffuxu";
    private TextView tv1 = null;
    private Button bt6 = null;
    private ProgressBar pb = null;

    String srcPath = "/data/Logs/";
    ///storage/emulated/0
    String desPath = Environment.getExternalStorageDirectory().toString();
    //String desPath = "/data/Logs/test/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_log);
        Log.d(TAG, "CopyLogActivity onCreate desPath=" + desPath);

       // int result = copyLogFiles(srcPath, desPath+"/00/");

        tv1 = (TextView) findViewById(R.id.tv1);
        bt6 = (Button) findViewById(R.id.button6);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask mAsyncTask = new MyAsyncTask(tv1, pb);
                mAsyncTask.execute(srcPath, desPath+"/");
            }
        });
    }



    //第一个参数Integer: 来自UI线程execute()的参数,也是doInBackground的参数
    //第二个参数Integer: 这是onProgressUpdate()的参数
    //第三个参数String: 这是doInBackground()的参数值

    class MyAsyncTask extends AsyncTask<String, Integer , Integer> {

        private TextView mTextView = null;
        private ProgressBar mProgressBar = null;

        //使用构造函数把MainActivity的参数传进来
        public MyAsyncTask(TextView mTextView, ProgressBar mProgressBar) {
            this.mTextView = mTextView;
            this.mProgressBar = mProgressBar;
        }

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "AsyncTask Starting...");
            mTextView.setText("AsyncTask Starting...");
        }


        //run as a other thread
        @Override
        protected Integer doInBackground(String... params) {
            Log.d(TAG, "doInBackground");
          /*  int i = 0;
            for(i=0; i<=100; i+=10){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.d(TAG, "Thread Sleep Error");
                    e.printStackTrace();
                }
            }
            //execute()有几个参数, params数组就有几个元素
            return i + params[0].intValue() + params[1].intValue() +"";*/
            int result = 0;
            String source = params[0];
            String destination = params[1];
            result = copyLogFiles(source, destination);
            return result;

        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "onProgressUpdate");
            int value = values[0];
            mProgressBar.setProgress(value);
        }

        //参数来自doInBackground的返回值
        @Override
        protected void onPostExecute(Integer result) {
            Log.d(TAG, "onPostExecute");
            String strResult = null;
            switch (result) {
                case -4:
                    strResult = "写文件出错";
                    break;
                case -3:
                    strResult = "创建目的文件夹出错";
                    break;
                case -2:
                    strResult = "访问源文件夹出错";
                    break;
                case -1:
                    strResult = "源文件夹不存在";
                    break;
                case 0:
                    strResult = "拷贝完成";
                    break;
                default:
                    strResult = "拷贝发生未知错误";
            }

            mTextView.setText("AsyncTask end " + strResult);
            Toast.makeText(CopyLogActivity.this, strResult, Toast.LENGTH_LONG).show();
        }

        //Copy File Function
        public int copyLogFiles(String src, String dst) {
            int ret = 1;
            File[] currentFiles;

            File targetDir = new File(dst);
            if (!targetDir.exists()) {
                if (!targetDir.mkdirs()) {
                    Log.e(TAG, "make target dir " + targetDir + " failed!");
                    return -3;
                }
            }

            File source = new File(src);
            if (!source.exists()) {
                return -1;
            }

            currentFiles = source.listFiles();
            if (currentFiles == null) {
                Log.e(TAG, "currentFiles is null");
                return -2;
            } else {
                //int len, j;
                //len = currentFiles.length;
                //for(j=0; j<len; j++){
                //    Log.d(TAG, "File is:"+currentFiles[j].getName());
                //}
            }


            for (int i = 0; i < currentFiles.length; i++) {
                if (currentFiles[i].isDirectory()) {
                    if (currentFiles[i].listFiles() == null) {
                        File eptFolder = new File(dst + "/" + currentFiles[i].getName());
                        if (!eptFolder.exists()) {
                            Log.d(TAG, "creat empty folder");
                            eptFolder.mkdirs();
                        }
                    } else {
                        ret = copyLogFiles(currentFiles[i].getPath() + "/", dst + "/" + currentFiles[i].getName());
                    }
                } else {
                    ret = copyFile(currentFiles[i].getPath(), targetDir + "/" + currentFiles[i].getName());
                }
            }
            return ret;
        }

        public int copyFile(String fromFile, String toFile) {
            Log.d(TAG, "write file:" + fromFile + " ---to---> " + toFile);
            try {
                InputStream fosfrom = new FileInputStream(fromFile);
                OutputStream fosto = new FileOutputStream(toFile);
                byte bt[] = new byte[1024 * 4];
                int c;
                while ((c = fosfrom.read(bt)) > 0) {
                    fosto.write(bt, 0, c);
                }
                fosto.flush();
                fosfrom.close();
                fosto.close();
                return 0;
            } catch (Exception ex) {
                return -4;
            }
        }

        //abandon block
        public void copyFolder(String oldPath, String newPath) {
            try {
                (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
                File a = new File(oldPath);
                String[] file = a.list();
                File temp = null;
                for (int i = 0; i < file.length; i++) {
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + file[i]);
                    } else {
                        temp = new File(oldPath + File.separator + file[i]);
                    }
                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(newPath + "/" +
                                (temp.getName()).toString());
                        byte[] b = new byte[1024 * 5];
                        int len;
                        while ((len = input.read(b)) != -1) {
                            output.write(b, 0, len);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                    if (temp.isDirectory()) {//如果是子文件夹
                        copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                    }
                }
            } catch (Exception e) {
                System.out.println("复制整个文件夹内容操作出错");
                e.printStackTrace();

            }

        }


    }

}
