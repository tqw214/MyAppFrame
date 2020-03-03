package com.viger.dagger.bean;

import javax.inject.Inject;

public class Student {

    private String message = "Student的实例是注解方式注入的";

    @Inject
    public Student() {

    }

    public String showMessage() {
        return message;
    }

}
