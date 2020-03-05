package com.viger.dagger.bean;

public class Student {

    private String message = "Student的实例是注解方式注入的";

    public Student() {

    }

    public String showMessage() {
        return message;
    }

}
