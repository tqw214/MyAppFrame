package com.viger.myappframe.base;

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();

}
