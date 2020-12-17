package com.my.mytest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

    @JsonProperty(value = "real_name")
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "realName='" + realName + '\'' +
                '}';
    }

}
