package com.koffuxu.myapplication.thirdlib.dagger2test;

/**
 * Created by XGF on 2017/11/21.
 * refer to:http://www.jianshu.com/p/1d84ba23f4d2
 * Dagger2 include 3 patten:
 *      1,Module:
 *      2,
 *      3,Component:Interface
 */

public class Cloth {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "布料";
    }
}
