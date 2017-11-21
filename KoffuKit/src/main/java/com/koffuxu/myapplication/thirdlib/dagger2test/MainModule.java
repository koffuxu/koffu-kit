package com.koffuxu.myapplication.thirdlib.dagger2test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by XGF on 2017/11/21.
 */

@Module
public class MainModule {
    @Provides
    public Cloth getCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("RED");
        return cloth;
    }
    @Provides
    public ClothesDaggerTest getClothes(){
        Cloth c = new Cloth();
        c.setColor("黑色");
        return new ClothesDaggerTest(c);
    }
}
