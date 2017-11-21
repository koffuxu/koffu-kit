package com.koffuxu.myapplication.thirdlib.dagger2test;

import dagger.Component;

/**
 * Created by XGF on 2017/11/21.
 */

@Component(modules = MainModule.class)
public interface MainComponet {
    void inJect(MainActivityTestDagger mainActivityTestDagger);
}
