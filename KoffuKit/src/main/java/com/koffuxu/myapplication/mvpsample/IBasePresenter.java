package com.koffuxu.myapplication.mvpsample;

import android.content.Context;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by XGF on 2017/11/9.
 */

public interface IBasePresenter {
    void submitData(Context context, List<EditText> list, ProgressBar progressBar);
    void submitData2(List<String> etlist);
    void initdata(List<EditText> list);
}
