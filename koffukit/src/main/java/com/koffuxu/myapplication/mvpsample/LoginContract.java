package com.koffuxu.myapplication.mvpsample;

/**
 * Created by koffuxu on 2017/11/11.
 */

public interface LoginContract {
    interface View extends IBaseView{
        void alterDailogin(int status);
    }

    interface Presenter extends IBasePresenter{

    }

    interface Mode {

    }
}
