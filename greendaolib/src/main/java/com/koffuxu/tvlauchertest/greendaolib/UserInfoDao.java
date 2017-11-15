package com.koffuxu.tvlauchertest.greendaolib;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class UserInfoDao {

    public static void main(String[] args) throws Exception {
        int version=1;
        String defaultPackage="com.koffuxu.myapplication.daogen";
        //创建模式对象，指定版本号和自动生成的bean对象的包名
        Schema schema=new Schema(version,defaultPackage);
        //指定自动生成的dao对象的包名,不指定则都DAO类生成在"test.greenDAO.bean"包中
        schema.setDefaultJavaPackageDao("com.koffuxu.myapplication.daogen");

        //添加实体
        addEntity(schema);

        String outDir="koffukit/src/main/java";
        //调用DaoGenerator().generateAll方法自动生成代码到之前创建的java-gen目录下
        new DaoGenerator().generateAll(schema,outDir);


    }

    private static void addEntity(Schema schema) {
        //用户信息表
        Entity entity = schema.addEntity("UserInfo");
        entity.addStringProperty("name");
        entity.addIntProperty("age");
        entity.addStringProperty("hobby");
        entity.addStringProperty("gender");
        entity.addIdProperty().primaryKey().autoincrement();
        Property fatherId = entity.addLongProperty("fatherId").getProperty();

        //父亲用户信息表
        Entity fatherEntity = schema.addEntity("FatherUserInfo");
        fatherEntity.addStringProperty("name");
        fatherEntity.addIdProperty().primaryKey();

        //关连外键
        entity.addToOne(fatherEntity, fatherId);



        //指定表名，如不指定，表名则为 Entity（即实体类名）
//        entity.setTableName("student");
        //给实体类中添加属性（即给test表中添加字段）
//        entity.addIdProperty().autoincrement();//添加Id,自增长
//        entity.addStringProperty("name").notNull();//添加String类型的name,不能为空
//        entity.addIntProperty("age");//添加Int类型的age
//        entity.addDoubleProperty("score");//添加Double的score
//        entity.addBooleanProperty("isShow");//是否显示在外面
//        entity.addIntProperty("status");
    }
}
