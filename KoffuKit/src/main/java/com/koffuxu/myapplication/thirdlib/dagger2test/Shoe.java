package com.koffuxu.myapplication.thirdlib.dagger2test;

import javax.inject.Inject;

/**
 * Created by XGF on 2017/11/21.
 */

public class Shoe {
    @Inject
    public Shoe() {
    }

    @Override
    public String toString() {
        return "鞋子";
    }
}
