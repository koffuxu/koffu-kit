package com.koffuxu.myapplication.mvpsample;

/**
 * Created by XGF on 2017/11/9.
 */
public class UserInfo_Legacy {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "UserInfo_Legacy{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    private String age;
    private String name;
    private String gender;
    private String hobby;
}
