package com.koffuxu.myapplication.thirdlib.dagger2test;

/**
 * Created by XGF on 2017/11/21.
 */

public class ClothesDaggerTest {
    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public String toString() {
        return cloth.getColor() + "衣服";
    }

    public ClothesDaggerTest(Cloth cloth) {
        this.cloth = cloth;
    }

    private Cloth cloth;

}
